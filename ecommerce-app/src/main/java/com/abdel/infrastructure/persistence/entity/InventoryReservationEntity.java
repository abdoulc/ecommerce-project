package com.abdel.infrastructure.persistence.entity;

import com.abdel.business.domain.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReservationEntity {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    private String orderId;

    private String productId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime expiryTime;

    private LocalDateTime createdAt;
}
