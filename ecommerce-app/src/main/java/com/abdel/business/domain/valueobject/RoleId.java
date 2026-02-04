package com.abdel.business.domain.valueobject;

public record RoleId (String value){
    public RoleId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("RoleId cannot be null or blank");
        }
    }

    public static RoleId newId() {
        return IdFactory.newId(RoleId::new);
    }
}
