package com.abdel.business.usecase.command.impl;

import com.abdel.business.domain.model.Role;
import com.abdel.business.domain.model.User;
import com.abdel.business.domain.valueobject.Email;
import com.abdel.business.domain.valueobject.Password;
import com.abdel.business.domain.valueobject.UserId;
import com.abdel.business.usecase.command.port.in.RegisterUserUseCase;
import com.abdel.business.usecase.command.port.out.UserRepository;
import com.abdel.business.usecase.input.RegisterUserInput;
import com.abdel.business.usecase.query.port.out.RoleQueryPort;
import com.abdel.infrastructure.conf.security.BCryptPasswordHasher;

import java.util.Set;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordHasher passwordHasher;

    private final RoleQueryPort roleQueryPort;

    public RegisterUserUseCaseImpl(final UserRepository userRepository, BCryptPasswordHasher passwordHasher, RoleQueryPort roleQueryPort) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.roleQueryPort = roleQueryPort;
    }

    @Override
    public void register(RegisterUserInput input) {
        UserId id = UserId.newId();
        Role defaultRole = roleQueryPort.findByName("ROLE_USER");
        String hashedPassword = passwordHasher.hash(input.password());
        User user = User.create(
                id,
                input.username(),
                new Email(input.email()),
                new Password(hashedPassword),
                Set.of(defaultRole)
        );
        userRepository.register(user);
    }
}
