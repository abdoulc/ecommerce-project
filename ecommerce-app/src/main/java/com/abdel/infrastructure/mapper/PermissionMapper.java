package com.abdel.infrastructure.mapper;

import com.abdel.business.domain.model.Permission;
import com.abdel.business.domain.valueobject.PermissionId;
import com.abdel.infrastructure.persistence.entity.PermissionEntity;

public class PermissionMapper {

    public static Permission toDomain(PermissionEntity entity) {
        return new Permission(
                new PermissionId(entity.getId()),
                entity.getName()
        );
    }

    public static PermissionEntity toEntity(Permission permission) {
        return new PermissionEntity(
                permission.id(),
                permission.name()
        );
    }
}
