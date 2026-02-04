package com.abdel.business.usecase.command.port.in;

import com.abdel.business.domain.model.UserView;

public interface TokenUseCase {
    String generateToken(UserView user);
}
