package com.abdel.web.mapper;

import com.abdel.business.usecase.input.AuthenticateUserInput;
import com.abdel.business.usecase.input.RegisterUserInput;
import com.abdel.web.generated.model.AuthLoginPostRequest;
import com.abdel.web.generated.model.AuthRegisterPostRequest;

public class AuthApiMapper {
    private AuthApiMapper() {

    }

    public static RegisterUserInput toInput(AuthRegisterPostRequest req) {
        return new RegisterUserInput(
                req.getUsername(),
                req.getEmail(),
                req.getPassword()
        );

    }

    public static AuthenticateUserInput tologinUserInput(AuthLoginPostRequest authLoginPostRequest) {
        return new AuthenticateUserInput(
                authLoginPostRequest.getUsername(),
                authLoginPostRequest.getPassword()
        );

    }
}
