package com.abdel.business.usecase.command.port.in;

import com.abdel.business.usecase.input.AuthenticateUserInput;

public interface AuthenticateUserUseCase {
    String authenticate(AuthenticateUserInput input);
}
