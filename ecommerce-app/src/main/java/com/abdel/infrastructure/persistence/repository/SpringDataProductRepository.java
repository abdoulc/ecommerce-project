package com.abdel.infrastructure.persistence.repository;

import com.abdel.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, String> {
}
