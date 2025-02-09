package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.model.ScourtBoardDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JsoupService {

    public ScourtBoardDetail getScourtBoardDetail(String html) {
        try {
            Document doc = Jsoup.parse(html);
            Element tableHor = doc.getElementsByClass("tableVer").first();

            ScourtBoardDetail.ScourtBoardDetailBuilder builder = ScourtBoardDetail.builder();
            Elements tr = tableHor.getElementsByTag("tr");

            for (Element trElement : tr) {
                Elements th = trElement.getElementsByTag("th");
                Elements td = trElement.getElementsByTag("td");

                for (int elementIndex = 0; elementIndex < th.size(); elementIndex++) {
                    Element thElement = th.get(elementIndex);
                    Element tdElement = td.get(elementIndex);

                    if ("매각기관".equals(thElement.text())) {
                        builder.seller(tdElement.text());
                    } else if ("관할법원".equals(thElement.text())) {
                        builder.court(tdElement.text());
                    } else if ("제목".equals(thElement.text())) {
                        builder.title(tdElement.text());
                    } else if ("작성일".equals(thElement.text())) {
                        builder.uploaded(tdElement.text());
                    } else if ("공고만료일".equals(thElement.text())) {
                        builder.due(tdElement.text());
                    } else if ("전화번호".equals(thElement.text())) {
                        builder.telephoneNumber(tdElement.text());
                    } else if ("조회수".equals(thElement.text())) {
                        builder.views(tdElement.text());
                    } else if ("첨부파일".equals(thElement.text())) {
                        String download = tdElement.child(0).attribute("href").getValue();
                        Matcher matcher = Pattern.compile(
                                "javascript:download\\('([^',]*)'"
                        ).matcher(download);

                        if (matcher.find()) {
                            builder.file(matcher.group(1));
                        }
                        builder.fileName(tdElement.text());
                    }
                }
            }

            return builder.build();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<Long> getSeqId(String html) {
        ArrayList<Long> result = new ArrayList<>();

        try {
            Document doc = Jsoup.parse(html);
            Element tableHor = doc.getElementsByClass("tableHor").first();
            Element tbody = tableHor.getElementsByTag("tbody").first();

            for (Element tr : tbody.getElementsByTag("tr")) {
                Elements td = tr.getElementsByTag("td");

                String referer = td.get(3).child(0).attribute("href").getValue();
                Matcher matcher = Pattern.compile("seq_id=([0-9]*)").matcher(referer);

                if (matcher.find()) {
                    Long seqId = Long.parseLong(matcher.group(1));
                    result.add(seqId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return result;
    }

    public Integer getLastPageIndex(String html) {

        try {
            Document doc = Jsoup.parse(html);
            Element pagelist = doc.getElementsByClass("pagelist").first();
            Element prev = pagelist.getElementsByClass("prev").first();
            String href = prev.attribute("href").getValue();

            String numPage = href.split("=")[1];
            return Integer.parseInt(numPage);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
