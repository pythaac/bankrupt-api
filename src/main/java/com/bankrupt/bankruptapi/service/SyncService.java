package com.bankrupt.bankruptapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.concurrent.CompletableFuture;

@Service
public class SyncService {
    private final ScourtService scourtService;

    private static LocalDateTime lastSyncTime;
    private static CompletableFuture<Void> syncTask = CompletableFuture.completedFuture(null);

    @Value("${sync.enabled:true}")
    private boolean syncEnabled;

    public SyncService(ScourtService scourtService) {
        this.scourtService = scourtService;
    }

    public LocalDateTime getSyncTime() {
        return lastSyncTime;
    }

    @Scheduled(fixedDelayString = "${sync.fixed-delay}")
    public void syncScourt() {
        if (!syncEnabled) {
            return;
        }
        if (!syncTask.isDone()) {
            throw new ConcurrentModificationException();
        }
        lastSyncTime = LocalDateTime.now();
        syncTask = CompletableFuture.runAsync(scourtService::updateDiff);
    }
}
