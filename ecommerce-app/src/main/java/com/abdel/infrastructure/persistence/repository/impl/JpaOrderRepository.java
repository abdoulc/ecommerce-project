package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.Order;
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
}
