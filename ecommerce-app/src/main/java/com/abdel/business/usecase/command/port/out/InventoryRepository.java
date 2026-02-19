package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.domain.valueobject.Quantity;

public interface InventoryRepository {
    void reserveInventory(OrderItem orderItem);

    void releaseInventory(ProductId productId, Quantity quantity);
}
