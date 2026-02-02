package com.abdel.business.usecase.command.port.out;

public interface PasswordHasher {
    String hash(String plainPassword);
    boolean matches(String raw, String hashed);

}
