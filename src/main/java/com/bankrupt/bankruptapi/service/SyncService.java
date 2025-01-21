package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

@Service
@AllArgsConstructor
public class SyncService {
    private final BoardRepository boardRepository;
    private final ScourtService scourtService;

    private static CompletableFuture<Void> syncTask = CompletableFuture.completedFuture(null);

    public LocalDateTime getSyncTime() {
        return boardRepository.findLatestUpdated();
    }

    // 1000ms * 60 * 60 = 1hour
    @Scheduled(fixedDelay = 3_600_000)
    public void syncScourt() {

        if (!syncTask.isDone()) {
            throw new ConcurrentModificationException();
        }
        syncTask = CompletableFuture.runAsync(scourtService::updateDiff);
    }
}
