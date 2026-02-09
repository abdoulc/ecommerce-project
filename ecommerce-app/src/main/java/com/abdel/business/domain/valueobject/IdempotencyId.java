package com.abdel.business.domain.valueobject;

public record IdempotencyId(String value) {

    public IdempotencyId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ProductId cannot be null or blank");
        }
    }

    public static IdempotencyId newId() {
        return IdFactory.newId(IdempotencyId::new);
    }
}
