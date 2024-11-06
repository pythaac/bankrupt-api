package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.feign.ScourtBoardClient;
import com.bankrupt.bankruptapi.dao.Board;
import com.bankrupt.bankruptapi.model.CourtBoardDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScourtService {
    private final ScourtBoardClient scourtBoardClient;
    private final JsoupService jsoupService;
    private final BoardService boardService;

    public void updateDiff() {
        List<Long> idsFromCourt = getAllBoards();
        List<Long> idsFromDb = boardService.getAllBoardIdList();

        // Not in DB -> insert
        List<Long> insertIdList = idsFromCourt.stream()
                .parallel()
                .filter(court -> !idsFromDb.contains(court))
                .toList();

        // Not in Court -> delete
        List<Long> deleteIdList = idsFromDb.stream()
                .parallel()
                .filter(db -> !idsFromCourt.contains(db))
                .toList();

        if (!insertIdList.isEmpty()) {
            ArrayList<Board> insertBoardList = new ArrayList<>();

            for (Long seqId : insertIdList) {
                CourtBoardDetail boardDetail = getBoardDetail(seqId);
                Board board = boardDetail.toBoard(seqId);
                insertBoardList.add(board);
            }

            boardService.saveBoardList(insertBoardList);
        }

        if (!deleteIdList.isEmpty()) {
            boardService.deleteByBoardIdList(deleteIdList);
        }
    }

    private ArrayList<Long> getAllBoards() {

        Map<String, Object> header = getScourtBoardHeader();
        String html = scourtBoardClient.getBoardPage(header, 5, 1);

        Integer lastPageIndex = jsoupService.getLastPageIndex(html);

        ArrayList<Long> courtBoards = new ArrayList<>();
        for(int pageIndex=1; pageIndex<=lastPageIndex; pageIndex++) {
            html = scourtBoardClient.getBoardPage(header, 5, pageIndex);
            ArrayList<Long> courtBoard = jsoupService.getSeqId(html);
            courtBoards.addAll(courtBoard);
        }

        return courtBoards;
    }

    private CourtBoardDetail getBoardDetail(Long seqId) {

        Map<String, Object> header = getScourtBoardHeader();

        String detailHtml = scourtBoardClient.getBoardDetailPage(header, seqId);
        CourtBoardDetail courtBoardDetail = jsoupService.getBoardDetails(detailHtml);

        return courtBoardDetail;
    }

    private Map<String, Object> getScourtBoardHeader() {

        Map<String, Object> header = new HashMap<>();

        List<String> accept = List.of(
                "text/html"
                , "application/xhtml+xml"
                , "application/xml;q=0.9"
                , "image/avif"
                , "image/webp"
                , "image/apng"
                , "*/*;q=0.8"
                , "application/signed-exchange;v=b3;q=0.7"
        );
        List<String> acceptEncoding = List.of(
                "gzip"
                , "deflate"
                , "br"
                , "zstd"
        );
        List<String> acceptLanguage = List.of(
                "ko-KR"
                , "ko;q=0.9"
                , "en-US;q=0.8"
                , "en;q=0.7"
        );
        List<String> secChUa = List.of(
                "\"Chromium\";v=\"130\""
                , "\"Google Chrome\";v=\"130\""
                , "\"Not?A_Brand\";v=\"99\""
        );


        header.put("Accept", accept);
        header.put("Accept-Encoding", acceptEncoding);
        header.put("Accept-Language", acceptLanguage);
        header.put("Connection", "keep-alive");
        header.put("Host", "www.scourt.go.kr");
        header.put("Sec-Fetch-Dest", "document");
        header.put("Sec-Fetch-Mode", "navigate");
        header.put("Sec-Fetch-Site", "none");
        header.put("Sec-Fetch-User", "?1");
        header.put("Upgrade-Insecure-Requests", "1");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");
        header.put("sec-ch-ua", secChUa);
        header.put("sec-ch-ua-mobile", "?0");
        header.put("sec-ch-ua-platform", "Windows");

        return header;
    }
}
