package com.abdel.business.usecase.input;

public record AuthenticateUserInput(
        String username,
        String password
) {
}
