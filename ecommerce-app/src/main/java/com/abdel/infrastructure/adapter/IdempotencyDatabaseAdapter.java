package com.abdel.infrastructure.adapter;

import com.abdel.business.domain.valueobject.IdempotencyId;
import com.abdel.business.usecase.command.port.out.IdempotencyPort;
import com.abdel.core.IdempotencyRecord;
import com.abdel.exceptions.IdempotencyConflictException;
import com.abdel.exceptions.IdempotencyInProgressException;
import com.abdel.infrastructure.persistence.entity.IdempotencyEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataIdempotencyRepository;
import com.abdel.infrastructure.util.RequestHashUtil;
import com.abdel.infrastructure.util.SerializationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Supplier;

import static com.abdel.core.IdempotencyStatus.*;

@Component
public class IdempotencyDatabaseAdapter implements IdempotencyPort {
    private final SpringDataIdempotencyRepository idempotencyRepository;

    @Autowired
    public IdempotencyDatabaseAdapter(SpringDataIdempotencyRepository idempotencyRepository) {
        this.idempotencyRepository = idempotencyRepository;
    }

    @Override
    public <T, R> T execute(String key, String operation, Duration ttl, R payload, Supplier<T> action, Class<T> responseType) {
        String requestHash = RequestHashUtil.hash(payload);

        Optional<IdempotencyEntity> existing =
                idempotencyRepository.findByIdempotencyKeyAndOperation(key, operation);

        if (existing.isPresent()) {
            return handleExisting(existing.get(), requestHash, responseType);
        }

        IdempotencyEntity entity = createInProgressRecord(
                key, operation, requestHash, ttl
        );

        try {
            idempotencyRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new IdempotencyInProgressException(key);
        }

        try {
            T result = action.get();

            entity.setResponsePayload(SerializationUtil.serialize(result));
            entity.setStatus(SUCCESS);
            idempotencyRepository.save(entity);

            return result;
        } catch (Exception ex) {
            entity.setStatus(FAILED);
            idempotencyRepository.save(entity);
            throw ex;
        }
    }

    private <T> T handleExisting(
            IdempotencyEntity entity,
            String requestHash,
            Class<T> responseType
    ) {
        if (entity.getExpiresAt() != null &&
                entity.getExpiresAt().isBefore(Instant.now())) {

            idempotencyRepository.delete(entity);
            throw new IdempotencyInProgressException(
                    "Idempotency record expired, retry request"
            );
        }

        if (!entity.getRequestHash().equals(requestHash)) {
            throw new IdempotencyConflictException(entity.getIdempotencyKey());
        }

        return switch (entity.getStatus()) {
            case SUCCESS -> com.abdel.util.SerializationUtil.deserialize(
                    entity.getResponsePayload(), responseType
            );
            case FAILED -> throw new IllegalStateException(
                    "Previous request failed, retry allowed"
            );
            case IN_PROGRESS -> throw new IdempotencyInProgressException(
                    entity.getIdempotencyKey()
            );
        };
    }

    private IdempotencyEntity createInProgressRecord(
            String key,
            String operation,
            String requestHash,
            Duration ttl
    ) {
        IdempotencyEntity entity = new IdempotencyEntity();
        entity.setId(IdempotencyId.newId().value());
        entity.setIdempotencyKey(key);
        entity.setOperation(operation);
        entity.setRequestHash(requestHash);
        entity.setStatus(IN_PROGRESS);
        entity.setCreatedAt(Instant.now());
        entity.setExpiresAt(Instant.now().plus(ttl));
        entity.setResponsePayload(null);
        return entity;
    }
}
