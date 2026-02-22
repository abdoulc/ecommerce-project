package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.Order;
import com.abdel.business.domain.model.enums.Currency;
import com.abdel.business.domain.model.enums.OrderStatus;
import com.abdel.business.domain.valueobject.Money;
import com.abdel.business.domain.valueobject.OrderId;
import com.abdel.business.usecase.command.port.out.OrderRepository;
import com.abdel.infrastructure.persistence.entity.OrderEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaOrderRepository implements OrderRepository {
    private final SpringDataOrderRepository springDataOrderRepository;

    public JpaOrderRepository(SpringDataOrderRepository springDataOrderRepository) {
        this.springDataOrderRepository = springDataOrderRepository;
    }

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = new OrderEntity(
                order.getOrderId().value(),
                order.getStatus(),
                order.getTotalAmount().amount(),
                order.getTotalAmount().currency().name()
        );
        springDataOrderRepository.save(orderEntity);
    }

    @Override
    public Order getById(OrderId orderId) {
        OrderEntity entity = springDataOrderRepository.findById(orderId.value()).orElse(null);
        if (entity == null) return null;
        return new Order(
                new OrderId(entity.getId()),
                entity.getStatus(),
                new Money(entity.getTotalAmount(), Currency.valueOf(entity.getCurrency()))
        );
    }

    @Override
    public void cancerlOrder(OrderId orderId) {
        OrderEntity entity = springDataOrderRepository.findById(orderId.value()).orElse(null);
        if (entity != null) {
            entity.setStatus(OrderStatus.CANCELLED);
            springDataOrderRepository.save(entity);
        }
    }
}
