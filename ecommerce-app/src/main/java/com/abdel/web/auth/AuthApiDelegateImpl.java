package com.abdel.web.auth;

import com.abdel.business.domain.model.UserView;
import com.abdel.business.usecase.command.port.in.AuthenticateUserUseCase;
import com.abdel.business.usecase.command.port.in.RegisterUserUseCase;
import com.abdel.business.usecase.command.port.in.TokenUseCase;
import com.abdel.business.usecase.input.AuthenticateUserInput;
import com.abdel.business.usecase.input.RegisterUserInput;
import com.abdel.web.generated.api.AuthApiDelegate;
import com.abdel.web.generated.model.AuthLoginPost200Response;
import com.abdel.web.generated.model.AuthLoginPostRequest;
import com.abdel.web.generated.model.AuthRegisterPostRequest;
import com.abdel.web.mapper.AuthApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthApiDelegateImpl implements AuthApiDelegate {

    private final RegisterUserUseCase registerUserUseCase;

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final TokenUseCase tokenUseCase;

    @Autowired
    public AuthApiDelegateImpl(RegisterUserUseCase registerUserUseCase, AuthenticateUserUseCase authenticateUserUseCase, TokenUseCase tokenUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.tokenUseCase = tokenUseCase;
    }

    public ResponseEntity<AuthLoginPost200Response> authLoginPost(AuthLoginPostRequest authLoginPostRequest) {
        AuthenticateUserInput authenticateUserInput = AuthApiMapper.tologinUserInput(authLoginPostRequest);
        UserView user = authenticateUserUseCase.authenticate(authenticateUserInput);
        String token = tokenUseCase.generateToken(user);
        return ResponseEntity.ok(new AuthLoginPost200Response().token(token));
    }

    public ResponseEntity<Void> authRegisterPost(AuthRegisterPostRequest authRegisterPostRequest) {
        RegisterUserInput registerUserInput = AuthApiMapper.toInput(authRegisterPostRequest);
        registerUserUseCase.register(registerUserInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
