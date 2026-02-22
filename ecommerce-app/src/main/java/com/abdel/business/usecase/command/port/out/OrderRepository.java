package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.Order;
import com.abdel.business.domain.valueobject.OrderId;

public interface OrderRepository {

    void save(Order order);

    Order getById(OrderId orderId);
}
