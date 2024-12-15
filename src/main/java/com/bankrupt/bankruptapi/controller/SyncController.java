package com.bankrupt.bankruptapi.controller;

import com.bankrupt.bankruptapi.model.SyncTime;
import com.bankrupt.bankruptapi.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController(value = "/v1/sync")
public class SyncController {
    private final SyncService syncService;

    @GetMapping(value = "/time")
    public SyncTime getSyncTime() {
        LocalDateTime syncTime = syncService.getSyncTime();

        return SyncTime.builder()
                .syncTime(syncTime)
                .build();
    }

    @PutMapping
    public void syncScourt() {
        syncService.syncScourt();
    }
}
