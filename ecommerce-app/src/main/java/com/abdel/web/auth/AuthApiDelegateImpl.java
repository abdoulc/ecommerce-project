package com.abdel.web.auth;

import com.abdel.business.usecase.command.port.in.RegisterUserUseCase;
import com.abdel.business.usecase.input.RegisterUserInput;
import com.abdel.web.generated.api.AuthApiDelegate;
import com.abdel.web.generated.model.AuthLoginPost200Response;
import com.abdel.web.generated.model.AuthLoginPostRequest;
import com.abdel.web.generated.model.AuthRegisterPostRequest;
import com.abdel.web.mapper.AuthApiMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthApiDelegateImpl implements AuthApiDelegate {

    private final RegisterUserUseCase registerUserUseCase;

    public AuthApiDelegateImpl(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    public ResponseEntity<AuthLoginPost200Response> authLoginPost(AuthLoginPostRequest authLoginPostRequest) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<Void> authRegisterPost(AuthRegisterPostRequest authRegisterPostRequest) {
        RegisterUserInput registerUserInput = AuthApiMapper.toInput(authRegisterPostRequest);
        registerUserUseCase.register(registerUserInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
