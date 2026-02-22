package com.abdel.infrastructure.persistence.repository;

import com.abdel.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderItemRepository extends JpaRepository<OrderItemEntity, String> {
}
