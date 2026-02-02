package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.Role;
import com.abdel.business.usecase.query.port.out.RoleQueryPort;
import com.abdel.infrastructure.mapper.RoleMapper;
import com.abdel.infrastructure.persistence.entity.RoleEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaRoleRepository implements RoleQueryPort {
    private final SpringDataRoleRepository springDataRoleRepository;

    @Autowired
    public JpaRoleRepository(SpringDataRoleRepository springDataRoleRepository) {
        this.springDataRoleRepository = springDataRoleRepository;
    }

    @Override
    public Role findByName(String name) {
        Optional<RoleEntity> roleEntityOptional = springDataRoleRepository.findByName(name);
        return roleEntityOptional.map(RoleMapper::toDomain).orElse(null);
    }
}
