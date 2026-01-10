package com.abdel.cleanup;

import com.abdel.core.IdempotencyRecord;
import com.abdel.core.IdempotencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IdempotencyCleaner {

    private final IdempotencyRepository repository;

    @Scheduled(cron = "0 0 * * * *")
    public void cleanupExpired() {
        Instant now = Instant.now();
        List<IdempotencyRecord> expired = repository.findAll().stream()
                .filter(r -> r.getExpiresAt() != null && r.getExpiresAt().isBefore(now))
                .toList();
        repository.deleteAll(expired);
    }
}

