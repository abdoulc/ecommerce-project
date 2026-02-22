package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.Order;
import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.valueobject.OrderId;

public interface InventoryReservationRepository {
    void createInventoryReservation(OrderId orderId, OrderItem orderItem);

}
