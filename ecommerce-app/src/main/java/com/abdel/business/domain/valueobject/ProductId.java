package com.abdel.business.domain.valueobject;

import java.util.UUID;

public record ProductId(String value) {

    public ProductId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ProductId cannot be null or blank");
        }
    }

    public static ProductId newId() {
        return new ProductId(UUID.randomUUID().toString());
    }
}
