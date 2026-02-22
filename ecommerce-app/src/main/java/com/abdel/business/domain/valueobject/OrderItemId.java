package com.abdel.business.domain.valueobject;

public record OrderItemId(String value) {

    public OrderItemId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("OrderId cannot be null or blank");
        }
    }

    public static OrderItemId newId() {
        return IdFactory.newId(OrderItemId::new);
    }
}
