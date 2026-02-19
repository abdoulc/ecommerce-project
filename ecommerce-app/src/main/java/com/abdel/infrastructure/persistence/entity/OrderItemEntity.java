package com.abdel.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemEntity {
        @Id
        @Column(name = "id", length = 36, nullable = false)
        private String id;

        @Column(name = "order_id", length = 36, nullable = false)
        private String orderId;

        @Column(name = "product_id", length = 36, nullable = false)
        private String productId;

        @Column(name = "quantity", nullable = false)
        private int quantity;

        @Column(name = "unit_price", precision = 12, scale = 2, nullable = false)
        private BigDecimal unitPrice;
    }

