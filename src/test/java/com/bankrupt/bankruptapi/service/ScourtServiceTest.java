package com.bankrupt.bankruptapi.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
class ScourtServiceTest {
    @Autowired
    private ScourtService scourtService;

    @Test
    void test() {
        scourtService.updateDiff();
    }
}