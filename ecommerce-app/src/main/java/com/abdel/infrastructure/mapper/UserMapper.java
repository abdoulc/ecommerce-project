package com.abdel.infrastructure.mapper;

import com.abdel.business.domain.model.User;
import com.abdel.business.domain.model.UserView;
import com.abdel.business.domain.valueobject.UserId;
import com.abdel.business.domain.valueobject.Username;
import com.abdel.infrastructure.auth.SecurityUser;
import com.abdel.infrastructure.persistence.entity.UserEntiy;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
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

    public static UserView securityUserToUserView(SecurityUser securityUser) {
        Set<String> authorities = securityUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return new UserView(
                new UserId(securityUser.getId()),
                new Username(securityUser.getUsername()),
                securityUser.getRole(),
                securityUser.isEnabled(),
                authorities
        );
    }
}
