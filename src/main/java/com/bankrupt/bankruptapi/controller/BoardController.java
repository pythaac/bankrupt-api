package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.service.ScourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final ScourtService scourtService;

    @GetMapping(value = "/board")
    public String test() {
        scourtService.updateDiff();
        return "done";
    }
}
