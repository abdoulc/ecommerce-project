package com.abdel.business.domain.valueobject;

public record PaymentId(String value) {

    public PaymentId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("PaymentId cannot be null or blank");
        }
    }

    public static PaymentId newId() {
        return IdFactory.newId(PaymentId::new);
    }
}
