package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.service.ScourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final ScourtService scourtService;

    @GetMapping(value = "/")
    public String test() {
        scourtService.updateDiff();
        return "done";
    }
}
