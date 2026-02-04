package com.abdel.business.domain.valueobject;

public record CommonId(String value) {
    public CommonId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or blank");
        }
    }

    public static CommonId newId() {
        return new CommonId(java.util.UUID.randomUUID().toString());
    }
}
