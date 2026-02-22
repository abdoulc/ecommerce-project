package com.abdel.infrastructure.persistence.entity;

import com.abdel.business.domain.model.IdempotencyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "idempotency",
        uniqueConstraints = @UniqueConstraint(columnNames = {"idempotency_key", "operation"})
)
@Data
public class IdempotencyEntity{

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(nullable = false)
    private String idempotencyKey;

    @Column(nullable = false)
    private String operation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdempotencyStatus status;

    @Column(length = 255, nullable = false)
    private String requestHash;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String responsePayload;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant expiresAt;
}
