package com.abdel.business.domain.valueobject;

public record ProviderReference(String value) {

    public ProviderReference {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ProviderReference cannot be null or blank");
        }
    }

    public static ProviderReference newId() {
        return IdFactory.newId(ProviderReference::new);
    }
}
