package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.Order;
import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.model.enums.ReservationStatus;
import com.abdel.business.domain.valueobject.OrderId;
import com.abdel.business.usecase.command.port.out.InventoryRepository;
import com.abdel.business.usecase.command.port.out.InventoryReservationRepository;
import com.abdel.infrastructure.persistence.entity.InventoryEntity;
import com.abdel.infrastructure.persistence.entity.InventoryReservationEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataInventoryRepository;
import com.abdel.infrastructure.persistence.repository.SpringDataInventoryResRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaInventoryReservationRepository implements InventoryReservationRepository {
    private final SpringDataInventoryResRepository SpringDataInventoryResRepository;

    public JpaInventoryReservationRepository(SpringDataInventoryResRepository SpringDataInventoryResRepository) {
        this.SpringDataInventoryResRepository = SpringDataInventoryResRepository;
    }

    @Override
    @Transactional
    public void createInventoryReservation(OrderId orderId, OrderItem orderItem) {
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(15);
        var inventoryReservation = new InventoryReservationEntity(
               UUID.randomUUID().toString(),
                orderId.value(),
               orderItem.getProductId().value(),
               orderItem.getQuantity().value(),
               ReservationStatus.RESERVED,
               expiry,
               LocalDateTime.now()
       );
        SpringDataInventoryResRepository.save(inventoryReservation);
    }
}
