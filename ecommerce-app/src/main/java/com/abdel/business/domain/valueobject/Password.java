package com.abdel.business.domain.valueobject;

public record Password(String value) {
    public Password {
        if (value == null || value.length() < 3) {
            throw new IllegalArgumentException("Password must be at least 3 characters long");
        }
    }
}
