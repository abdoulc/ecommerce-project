package com.abdel.infrastructure.persistence.entity;

import com.abdel.business.domain.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    private String orderId;

    private BigDecimal amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String provider;

    private String providerReference;

    private LocalDateTime createdAt;

    private LocalDateTime completedAt;
}
