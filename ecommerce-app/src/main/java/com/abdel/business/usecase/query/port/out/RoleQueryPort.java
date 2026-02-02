package com.abdel.business.usecase.query.port.out;

import com.abdel.business.domain.model.Role;

public interface RoleQueryPort {
    Role findByName(String name);
}
