package com.bankrupt.bankruptapi.service;

import com.bankrupt.bankruptapi.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.concurrent.locks.ReentrantLock;

@Service
@AllArgsConstructor
public class SyncService {
    private final BoardRepository boardRepository;
    private final ScourtService scourtService;

    private static final ReentrantLock lock = new ReentrantLock();

    public LocalDateTime getSyncTime() {
        return boardRepository.findLatestUpdated();
    }

    public void syncScourt() {

        if (lock.isLocked()) {
            throw new ConcurrentModificationException();
        }
        lock.lock();
        scourtService.updateDiff(lock);
    }
}
