package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.valueobject.OrderId;
import com.abdel.business.domain.valueobject.OrderItemId;
import com.abdel.business.usecase.command.port.out.OrderItemRepository;
import com.abdel.infrastructure.persistence.entity.OrderItemEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataOrderItemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaOrderItemRepository implements OrderItemRepository {

    private final SpringDataOrderItemRepository springDataOrderItemRepository;

    public JpaOrderItemRepository(SpringDataOrderItemRepository springDataOrderItemRepository) {
        this.springDataOrderItemRepository = springDataOrderItemRepository;
    }

    @Override
    public void save(OrderId orderId, OrderItem orderItem) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderId(orderId.value());
        orderItemEntity.setId(OrderItemId.newId().value());
        orderItemEntity.setProductId(orderItem.getProductId().value());
        orderItemEntity.setQuantity(orderItem.getQuantity().value());
        orderItemEntity.setUnitPrice(orderItem.getPrice().amount());
        springDataOrderItemRepository.save(orderItemEntity);
    }
}
