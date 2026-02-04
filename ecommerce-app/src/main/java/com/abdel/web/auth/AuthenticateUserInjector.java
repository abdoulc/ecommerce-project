package com.abdel.web.auth;

import com.abdel.business.usecase.command.impl.AuthenticateUserUseCaseImpl;
import com.abdel.business.usecase.command.impl.TokenUseCaseImpl;
import com.abdel.business.usecase.command.port.in.AuthenticateUserUseCase;
import com.abdel.business.usecase.command.port.in.TokenUseCase;
import com.abdel.business.usecase.command.port.out.AuthenticationPort;
import com.abdel.business.usecase.command.port.out.TokenPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticateUserInjector {
    private final AuthenticationPort authenticationPort;

    private final TokenPort tokenPort;

    @Autowired
    public AuthenticateUserInjector(AuthenticationPort authenticationPort, TokenPort tokenPort) {
        this.authenticationPort = authenticationPort;
        this.tokenPort = tokenPort;
    }

    @Bean
    AuthenticateUserUseCase authenticateUserUseCase(){
      return new AuthenticateUserUseCaseImpl(authenticationPort);
    }

    @Bean
    TokenUseCase tokenUseCase(){
        return new TokenUseCaseImpl(tokenPort);
    }

}
