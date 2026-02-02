package com.abdel.business.domain.model;

import com.abdel.business.domain.valueobject.PermissionId;

public class Permission {
    private final PermissionId id;
    private final String name;

    public Permission(PermissionId id, String name) {
        this.id = id;
        this.name = name;
    }
    public String name() {
        return name;
    }

    public String id() {
        return id.value();
    }


}
