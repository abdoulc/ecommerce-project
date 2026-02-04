package com.abdel.business.domain.valueobject;

public record Email(String value) {
    public Email {
        if (value == null || value.isBlank() || !value.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
