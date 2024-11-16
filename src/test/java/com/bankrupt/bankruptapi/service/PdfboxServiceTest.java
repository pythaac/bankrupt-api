package com.bankrupt.bankruptapi.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PdfboxServiceTest {
    @Autowired
    PdfboxService pdfboxService;

    @Test
    void test() {
        String pdf = pdfboxService.getPdfTextByScourtUrl("1730349566354_133926.pdf", "test.pdf");
        System.out.println(pdf);
    }
}