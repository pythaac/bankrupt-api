package com.bankrupt.bankruptapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScourtServiceTest {
    @Autowired
    private ScourtService scourtService;

    @Test
    void test() {
        scourtService.updateDiff();
    }
}