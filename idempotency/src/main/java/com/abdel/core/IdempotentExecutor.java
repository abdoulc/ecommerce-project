package com.abdel.core;

import com.abdel.exceptions.IdempotencyConflictException;
import com.abdel.exceptions.IdempotencyInProgressException;
import com.abdel.util.RequestHashUtil;
import com.abdel.util.SerializationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Supplier;

import static com.abdel.core.IdempotencyStatus.*;

@Component
@RequiredArgsConstructor
public class IdempotentExecutor {

    private final IdempotencyRepository repository;

    @Transactional
    public <T, R> T execute(
            String key,
            String operation,
            Duration ttl,
            R requestPayload,
            Supplier<T> action,
            Class<T> responseType
    ) {
        String requestHash = RequestHashUtil.hash(requestPayload);

        Optional<IdempotencyRecord> existing =
                repository.findByIdempotencyKeyAndOperation(key, operation);

        if (existing.isPresent()) {
            return handleExisting(existing.get(), requestHash, responseType);
        }

        IdempotencyRecord record = createInProgressRecord(
                key, operation, requestHash, ttl
        );

        try {
            repository.save(record); // protected by DB unique constraint
        } catch (DataIntegrityViolationException e) {
            // Another request won the race
            throw new IdempotencyInProgressException(key);
        }

        try {
            T result = action.get();

            record.setResponsePayload(SerializationUtil.serialize(result));
            record.setStatus(SUCCESS);
            repository.save(record);

            return result;
        } catch (Exception ex) {
            record.setStatus(FAILED);
            repository.save(record);
            throw ex;
        }
    }

    private <T> T handleExisting(
            IdempotencyRecord record,
            String requestHash,
            Class<T> responseType
    ) {
        // TTL safety
        if (record.getExpiresAt() != null &&
                record.getExpiresAt().isBefore(Instant.now())) {

            repository.delete(record);
            throw new IdempotencyInProgressException(
                    "Idempotency record expired, retry request"
            );
        }

        if (!record.getRequestHash().equals(requestHash)) {
            throw new IdempotencyConflictException(record.getIdempotencyKey());
        }

        return switch (record.getStatus()) {
            case SUCCESS -> SerializationUtil.deserialize(
                    record.getResponsePayload(), responseType
            );
            case FAILED -> throw new IllegalStateException(
                    "Previous request failed, retry allowed"
            );
            case IN_PROGRESS -> throw new IdempotencyInProgressException(
                    record.getIdempotencyKey()
            );
        };
    }

    private IdempotencyRecord createInProgressRecord(
            String key,
            String operation,
            String requestHash,
            Duration ttl
    ) {
        IdempotencyRecord record = new IdempotencyRecord();
        record.setIdempotencyKey(key);
        record.setOperation(operation);
        record.setRequestHash(requestHash);
        record.setStatus(IN_PROGRESS);
        record.setCreatedAt(Instant.now());
        record.setExpiresAt(Instant.now().plus(ttl));
        record.setResponsePayload(null);
        return record;
    }
}
