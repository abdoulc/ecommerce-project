package com.abdel.business.usecase.command.port.in;

import com.abdel.business.usecase.input.RegisterUserInput;

public interface RegisterUserUseCase {
    void register(RegisterUserInput input);
}
