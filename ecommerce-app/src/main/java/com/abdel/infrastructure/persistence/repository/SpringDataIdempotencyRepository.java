package com.abdel.infrastructure.persistence.repository;

import com.abdel.infrastructure.persistence.entity.IdempotencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataIdempotencyRepository extends JpaRepository<IdempotencyEntity, UUID> {
    Optional<IdempotencyEntity> findByIdempotencyKeyAndOperation(String key, String operation);

}
