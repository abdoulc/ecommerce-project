package com.abdel.business.usecase.input;

public record RegisterUserInput  (
        String username,
        String email,
        String password
) {
}
