package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.domain.valueobject.Quantity;
import com.abdel.business.usecase.command.port.out.InventoryRepository;
import com.abdel.infrastructure.persistence.entity.InventoryEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataInventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaInventoryRepository implements InventoryRepository {
    private final SpringDataInventoryRepository springDataInventoryRepository;

    public JpaInventoryRepository(SpringDataInventoryRepository springDataInventoryRepository) {
        this.springDataInventoryRepository = springDataInventoryRepository;
    }

    @Override
    @Transactional
    public void reserveInventory(OrderItem orderItem) {
        Optional<InventoryEntity> inventoryOpt = springDataInventoryRepository.findByProductId(orderItem.getProductId().value());
        if (inventoryOpt.isPresent()) {
            InventoryEntity inventory = inventoryOpt.get();
            if (inventory.getAvailableQuantity() >= orderItem.getQuantity().value()) {
                inventory.setAvailableQuantity(inventory.getAvailableQuantity() - orderItem.getQuantity().value());
                inventory.setReservedQuantity(inventory.getReservedQuantity() + orderItem.getQuantity().value());
                springDataInventoryRepository.save(inventory);
            } else {
                throw new RuntimeException("Not enough inventory available");
            }
        } else {
            throw new RuntimeException("Product not found in inventory");
        }
    }

    @Override
    public void releaseInventory(ProductId productId, Quantity quantity) {
        Optional<InventoryEntity> inventoryOpt = springDataInventoryRepository.findByProductId(productId.value());
        if (inventoryOpt.isPresent()) {
            InventoryEntity inventory = inventoryOpt.get();
            int reservedQuantity = inventory.getReservedQuantity();
            int quantityValue = quantity.value();
            if (quantityValue > reservedQuantity) {
                throw new RuntimeException("Invalid release quantity");
            }
            inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantityValue);
            inventory.setReservedQuantity(reservedQuantity - quantityValue);
            springDataInventoryRepository.save(inventory);
        } else {
            throw new RuntimeException("Product not found in inventory");
        }
    }
}
