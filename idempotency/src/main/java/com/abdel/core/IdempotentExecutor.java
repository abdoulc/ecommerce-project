package com.abdel.core;


import com.abdel.exceptions.IdempotencyConflictException;
import com.abdel.exceptions.IdempotencyInProgressException;
import com.abdel.util.RequestHashUtil;
import com.abdel.util.SerializationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Supplier;

import static com.abdel.core.IdempotencyStatus.*;

@Component
@RequiredArgsConstructor
public class IdempotentExecutor {

    private final IdempotencyRepository repository;

    public <T, R> T execute(
            String key,
            String operation,
            Duration timeToLive,
            R requestPayload,
            Supplier<T> action,
            Class<T> responseType
    ) {
        Optional<IdempotencyRecord> existing = repository.findByIdempotencyKeyAndOperation(key, operation);
        String requestHashed = RequestHashUtil.hash(requestPayload);

        if (existing.isPresent()) {
            IdempotencyRecord idempotencyRecord = existing.get();
            if (!idempotencyRecord.getRequestHash().equals(requestHashed)) {
                throw new IdempotencyConflictException(key);
            }
            switch (idempotencyRecord.getStatus()) {
                case SUCCESS, FAILED->{
                    return SerializationUtil.deserialize(idempotencyRecord.getResponsePayload(), responseType);
                }
                case IN_PROGRESS-> throw new IdempotencyInProgressException(key);
            }
        }

        IdempotencyRecord idempotencyRecord = buildIdempotencyRecord(key, operation, timeToLive, requestHashed, requestPayload);
        repository.save(idempotencyRecord);

        try {
            T result = action.get();
            idempotencyRecord.setResponsePayload(SerializationUtil.serialize(result));
            idempotencyRecord.setStatus(SUCCESS);
            repository.save(idempotencyRecord);
            return result;
        } catch (Exception e) {
            idempotencyRecord.setStatus(FAILED);
            repository.save(idempotencyRecord);
            throw e;
        }
        
    }
    
    private IdempotencyRecord buildIdempotencyRecord(String key, String operation, Duration ttl, String requestHashed, Object requestPayload){
        final IdempotencyRecord idempotencyRecord = new IdempotencyRecord();
        idempotencyRecord.setIdempotencyKey(key);
        idempotencyRecord.setOperation(operation);
        idempotencyRecord.setResponsePayload(SerializationUtil.serialize(requestPayload));
        idempotencyRecord.setRequestHash(requestHashed);
        idempotencyRecord.setStatus(IN_PROGRESS);
        idempotencyRecord.setCreatedAt(Instant.now());
        idempotencyRecord.setExpiresAt(Instant.now().plus(ttl));
        return idempotencyRecord;
    }
}
