package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.Order;

public interface OrderRepository {

    void save(Order order);
}
