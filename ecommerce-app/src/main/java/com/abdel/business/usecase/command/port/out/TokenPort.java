package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.UserView;

public interface TokenPort {
    String generateToken(UserView user);
    String extractUsername(String token);

    boolean validateToken(String token, UserView user);
}
