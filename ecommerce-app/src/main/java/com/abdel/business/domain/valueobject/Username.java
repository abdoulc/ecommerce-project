package com.abdel.business.domain.valueobject;

public record Username(String value) {

    public Username {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (value.length() < 3 || value.length() > 20) {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters");
        }
        if (!value.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Username can only contain alphanumeric characters and underscores");
        }
    }
}
