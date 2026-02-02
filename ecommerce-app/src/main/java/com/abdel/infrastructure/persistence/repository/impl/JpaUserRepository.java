package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.User;
import com.abdel.business.usecase.command.port.out.UserRepository;
import com.abdel.infrastructure.mapper.UserMapper;
import com.abdel.infrastructure.persistence.entity.UserEntiy;
import com.abdel.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepository implements UserRepository {
    private final SpringDataUserRepository springDataUserRepository;

    public JpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public void register(User user) {
        UserEntiy userEntiy = UserMapper.toEntity(user);
        springDataUserRepository.save(userEntiy);
    }
}
