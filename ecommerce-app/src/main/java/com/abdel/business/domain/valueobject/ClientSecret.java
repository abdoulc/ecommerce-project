package com.abdel.business.domain.valueobject;

public record ClientSecret(String value) {

    public ClientSecret {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ClientSecret cannot be null or blank");
        }
    }

    public static ClientSecret newId() {
        return IdFactory.newId(ClientSecret::new);
    }
}
