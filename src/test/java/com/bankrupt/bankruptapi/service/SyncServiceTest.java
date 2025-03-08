package com.bankrupt.bankruptapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SyncServiceTest {
    @Autowired
    private SyncService syncService;

    @Test
    void getSyncTime_syncTimeIsNull() {
        LocalDateTime syncTime = syncService.getSyncTime();
        assertNull(syncTime);
    }
}