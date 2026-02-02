package com.abdel.infrastructure.mapper;

import com.abdel.business.domain.model.Role;
import com.abdel.business.domain.valueobject.RoleId;
import com.abdel.infrastructure.persistence.entity.RoleEntity;

import java.util.stream.Collectors;

public class RoleMapper {
    public static Role toDomain(RoleEntity entity) {
        return new Role(
                new RoleId(entity.getId()),
                entity.getName(),
                entity.getPermissions()
                        .stream()
                        .map(PermissionMapper::toDomain)
                        .collect(Collectors.toSet())
        );
    }

    public static RoleEntity toEntity(Role role) {
        return new RoleEntity(
                new RoleId(role.id()).value(),
                role.name(),
                role.getPermissions()
                        .stream()
                        .map(PermissionMapper::toEntity)
                        .collect(Collectors.toSet())
        );
    }
}
