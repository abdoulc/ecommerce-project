package com.abdel.business.usecase.command.impl;

import com.abdel.business.domain.model.UserView;
import com.abdel.business.usecase.command.port.in.AuthenticateUserUseCase;
import com.abdel.business.usecase.command.port.out.AuthenticationPort;
import com.abdel.business.usecase.input.AuthenticateUserInput;

public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {
    private final AuthenticationPort authenticationPort;

    public AuthenticateUserUseCaseImpl(AuthenticationPort authenticationPort) {
        this.authenticationPort = authenticationPort;
    }

    @Override
    public UserView authenticate(AuthenticateUserInput input) {
        return authenticationPort.authenticate(input);
    }
}
