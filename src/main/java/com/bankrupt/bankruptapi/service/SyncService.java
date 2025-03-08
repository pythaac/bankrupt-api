package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class SyncService {
    private final BoardRepository boardRepository;
    private final ScourtService scourtService;

    private static LocalDateTime lastSyncTime;
    private static CompletableFuture<Void> syncTask = CompletableFuture.completedFuture(null);

    public LocalDateTime getSyncTime() {
        return lastSyncTime;
    }

    @Scheduled(fixedDelayString = "${sync.scheduled}")
    public void syncScourt() {
        lastSyncTime = LocalDateTime.now();

        if (!syncTask.isDone()) {
            throw new ConcurrentModificationException();
        }
        syncTask = CompletableFuture.runAsync(scourtService::updateDiff);
    }
}
