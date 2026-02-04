package com.abdel.business.usecase.command.port.in;

import com.abdel.business.domain.model.UserView;
import com.abdel.business.usecase.input.AuthenticateUserInput;

public interface AuthenticateUserUseCase {
    UserView authenticate(AuthenticateUserInput input);
}
