package com.abdel.business.domain.valueobject;

import java.util.Objects;

public record UserId(String value) {
    public UserId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be null or blank");
        }
        Objects.requireNonNull(value, "UserId cannot be null");

    }

    public static UserId newId() {
        return new UserId(java.util.UUID.randomUUID().toString());
    }
}
