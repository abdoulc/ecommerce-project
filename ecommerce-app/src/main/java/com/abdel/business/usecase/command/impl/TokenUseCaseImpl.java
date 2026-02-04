package com.abdel.business.usecase.command.impl;

import com.abdel.business.domain.model.UserView;
import com.abdel.business.usecase.command.port.in.TokenUseCase;
import com.abdel.business.usecase.command.port.out.TokenPort;

public class TokenUseCaseImpl implements TokenUseCase {
    private final TokenPort tokenPort;

    public TokenUseCaseImpl(TokenPort tokenPort) {
        this.tokenPort = tokenPort;
    }

    @Override
    public String generateToken(UserView user) {
        return tokenPort.generateToken(user);
    }
}
