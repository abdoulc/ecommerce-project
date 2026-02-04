package com.abdel.infrastructure.persistence.repository;

import com.abdel.infrastructure.persistence.entity.UserEntiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntiy, Long> {
    Optional<UserEntiy> findByUsername(String username);

}
