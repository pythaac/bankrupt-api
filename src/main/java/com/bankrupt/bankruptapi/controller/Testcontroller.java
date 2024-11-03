package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.model.Board;
import com.bankrupt.bankruptapi.service.ScourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class Testcontroller {
    private final ScourtService scourtService;

    @GetMapping(value = "/")
    public ArrayList<Board> test() {
        return scourtService.test();
    }
}
