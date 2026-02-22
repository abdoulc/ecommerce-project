package com.abdel.web.order;

import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.model.enums.Currency;
import com.abdel.business.domain.valueobject.*;
import com.abdel.business.usecase.input.CreateOrderInput;
import com.abdel.business.usecase.input.CreatePaymentInput;
import com.abdel.business.usecase.response.OrderResponseDTO;
import com.abdel.web.generated.model.CreateOrderRequest;
import com.abdel.web.generated.model.OrderResponse;
import com.abdel.web.generated.model.PaymentRequest;

import java.util.List;

public class OrderApiMapper {
    public static CreateOrderInput toInput(String idempotencyKey, CreateOrderRequest createOrderRequest) {
        List<OrderItem> orderItems = mapToOrderItems(createOrderRequest);
        return new CreateOrderInput(new IdempotencyKey(idempotencyKey), orderItems);
    }

    private static List<OrderItem> mapToOrderItems(CreateOrderRequest request) {
        return request.getItems().stream()
                .map(item->(
                        new OrderItem(
                                new ProductId(item.getProductId()),
                                new Quantity(item.getQuantity()),
                                new Money(item.getPrice(), Currency.EUR)
                        )
                        ))
                .toList();
    }

    public static OrderResponse toResponse(OrderResponseDTO orderResponseDTO) {
        return new OrderResponse()
                .orderId(orderResponseDTO.orderId())
                .status(orderResponseDTO.status().name())
                .totalAmount(orderResponseDTO.totalAmount());
    }

    public static CreatePaymentInput toCreatePaymentInput(String idempotencyKey, String orderId, PaymentRequest paymentRequest) {
        return new CreatePaymentInput(new IdempotencyKey(idempotencyKey), new OrderId(orderId));
    }
}
