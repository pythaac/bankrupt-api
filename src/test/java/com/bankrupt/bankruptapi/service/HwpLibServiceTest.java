package com.bankrupt.bankruptapi.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class HwpLibServiceTest {
    @Autowired
    HwpLibService hwpLibService;

    @Test
    void getHwpTextByScourtUrl() {
        String hwp = hwpLibService.getHwpTextByScourtUrl("1729577132245_150532.hwp", "test.hwp");
        System.out.println(hwp);
    }
}