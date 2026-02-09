package com.abdel.infrastructure.persistence.entity;

import com.abdel.core.IdempotencyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "idempotency_records",
        uniqueConstraints = @UniqueConstraint(columnNames = {"idempotencyKey", "operation"})
)
@Data
public class IdempotencyEntity{

    @Id
    @GeneratedValue
    private UUID id;

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
