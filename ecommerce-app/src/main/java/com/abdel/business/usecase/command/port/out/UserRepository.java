package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.User;

public interface UserRepository {
    void register(User user);
}
