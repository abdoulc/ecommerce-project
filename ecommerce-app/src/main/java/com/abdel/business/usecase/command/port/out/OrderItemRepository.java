package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.valueobject.OrderId;

public interface OrderItemRepository {

     void save(OrderId orderId, OrderItem orderItem);
}
