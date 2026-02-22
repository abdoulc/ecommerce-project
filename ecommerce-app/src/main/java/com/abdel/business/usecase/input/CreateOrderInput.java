package com.abdel.business.usecase.input;

import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.valueobject.IdempotencyKey;

import java.util.List;

public record CreateOrderInput(IdempotencyKey idempotencyKey, List<OrderItem> orderItems) {

     public CreateOrderInput {
         if (orderItems == null || orderItems.isEmpty()) {
             throw new IllegalArgumentException("Order must contain at least one item");
         }
     }

}
