package com.abdel.infrastructure.mapper;

import com.abdel.business.domain.model.User;
import com.abdel.infrastructure.persistence.entity.UserEntiy;

import java.util.Collections;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntiy toEntity(User user) {

        return new UserEntiy(
                user.getUserId().value(),
                user.getUsername(),
                user.getEmail().value(),
                user.getPassword().value(),
                user.getRoles().stream()
                        .map(RoleMapper::toEntity)
                        .collect(Collectors.toSet()));
    }
}
