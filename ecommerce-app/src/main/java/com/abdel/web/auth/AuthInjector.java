package com.abdel.web.auth;

import com.abdel.business.usecase.command.impl.RegisterUserUseCaseImpl;
import com.abdel.business.usecase.command.port.in.RegisterUserUseCase;
import com.abdel.business.usecase.command.port.out.UserRepository;
import com.abdel.business.usecase.query.port.out.RoleQueryPort;
import com.abdel.infrastructure.conf.security.BCryptPasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthInjector {
    private final UserRepository userRepository;
    private final BCryptPasswordHasher passwordHasher;
    private final RoleQueryPort roleQueryPort;

    @Autowired
    public AuthInjector(UserRepository userRepository, BCryptPasswordHasher passwordHasher, RoleQueryPort roleQueryPort) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.roleQueryPort = roleQueryPort;
    }
    @Bean
    RegisterUserUseCase registerUserUseCase(){
      return new RegisterUserUseCaseImpl(userRepository, passwordHasher, roleQueryPort);
    }
}
