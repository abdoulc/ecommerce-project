package com.abdel.business.usecase.input;

import com.abdel.business.domain.valueobject.IdempotencyKey;
import com.abdel.business.domain.valueobject.OrderId;

public record CreatePaymentInput(IdempotencyKey idempotencyKey, OrderId orderId) {

     public CreatePaymentInput {
          if (orderId == null) {
               throw new IllegalArgumentException("Order ID cannot be null");
          }
          if(idempotencyKey == null) {
               throw new IllegalArgumentException("Idempotency key cannot be null");
          }
     }
}
