package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.UserView;
import com.abdel.business.usecase.command.port.out.AuthenticationPort;
import com.abdel.business.usecase.input.AuthenticateUserInput;
import com.abdel.infrastructure.auth.SecurityUser;
import com.abdel.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SpringAuthenticationImpl implements AuthenticationPort {
    private final AuthenticationManager authenticationManager;
    @Autowired
    public SpringAuthenticationImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public UserView authenticate(AuthenticateUserInput input) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(input.username(), input.password())
                );

        SecurityUser securityUser =
                (SecurityUser) authentication.getPrincipal();
        return UserMapper.securityUserToUserView(securityUser);
    }
}
