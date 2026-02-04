package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.User;
import com.abdel.business.domain.model.UserView;
import com.abdel.business.usecase.input.AuthenticateUserInput;

public interface AuthenticationPort {
    UserView authenticate(AuthenticateUserInput input);
}
