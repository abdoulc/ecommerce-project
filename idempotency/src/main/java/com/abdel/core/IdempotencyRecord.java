package com.abdel.core;

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
public class IdempotencyRecord {

    @Id
    @GeneratedValue
    private UUID id;

    private String idempotencyKey;
    private String operation;

    @Enumerated(EnumType.STRING)
    private IdempotencyStatus status;

    @Lob
    private String requestPayload;
    private String requestHash;

    @Lob
    private String responsePayload;

    private Instant createdAt;
    private Instant expiresAt;
}
