package com.abdel.business.domain.valueobject;

public record IdempotencyKey(String value) {

    public IdempotencyKey {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("IdempotencyKey cannot be null or blank");
        }
    }

}
