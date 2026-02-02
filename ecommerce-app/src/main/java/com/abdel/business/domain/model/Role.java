package com.abdel.business.domain.model;

import com.abdel.business.domain.valueobject.RoleId;

import java.util.HashSet;
import java.util.Set;

public class Role {

    private final RoleId id;
    private final String name;
    private final Set<Permission> permissions;

    public Role(RoleId id, String name, Set<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = Set.copyOf(permissions);
    }

    public String name() {
        return name;
    }

    public String id() {
        return id.value();
    }

    public Set<Permission> getPermissions() {
        return new HashSet<>(permissions);
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}
