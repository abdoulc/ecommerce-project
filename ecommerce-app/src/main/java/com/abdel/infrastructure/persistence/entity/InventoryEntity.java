package com.abdel.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    private String productId;

    private int availableQuantity;

    private int reservedQuantity;

    @Version
    private Long version;

    public void reserve(int quantity) {
        if (quantity > availableQuantity) {
            throw new RuntimeException("Not enough stock available");
        }

        this.availableQuantity -= quantity;
        this.reservedQuantity += quantity;
    }

    public void release(int quantity) {
        if (quantity > reservedQuantity) {
            throw new RuntimeException("Invalid release quantity");
        }

        this.reservedQuantity -= quantity;
        this.availableQuantity += quantity;
    }
}

