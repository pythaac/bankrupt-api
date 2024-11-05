package com.bankrupt.bankruptapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "scourtBoardClient", url = "https://www.scourt.go.kr")
public interface ScourtBoardClient {

    @PostMapping("/portal/notice/realestate/RealNoticeList.work")
    String getBoardPage(
            @RequestHeader Map<String, Object> header
            , @RequestParam("pageSize") Integer pageSize
            , @RequestParam("pageIndex") Integer pageIndex
    );

    @PostMapping("/portal/notice/realestate/RealNoticeView.work")
    String getBoardDetailPage(
            @RequestHeader Map<String, Object> header
            , @RequestParam("seq_id") Long seq_id
    );
}
