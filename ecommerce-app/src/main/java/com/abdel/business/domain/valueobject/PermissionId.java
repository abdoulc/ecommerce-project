package com.abdel.business.domain.valueobject;

public record PermissionId(String value) {
    public PermissionId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("PermissionId cannot be null or blank");
        }
    }

    public static PermissionId newId() {
        return IdFactory.newId(PermissionId::new);
    }
}
