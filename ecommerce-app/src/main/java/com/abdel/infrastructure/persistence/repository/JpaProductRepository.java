package com.abdel.infrastructure.persistence.repository;

import com.abdel.business.domain.model.Product;
import com.abdel.business.usecase.command.port.out.ProductRepository;
import com.abdel.infrastructure.mapper.ProductMapper;
import com.abdel.infrastructure.persistence.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProductRepository implements ProductRepository {
    private final SpringDataProductRepository springDataProductRepository;

    public JpaProductRepository(SpringDataProductRepository springDataProductRepository) {
        this.springDataProductRepository = springDataProductRepository;
    }

    @Override
    public void save(Product product) {
        ProductEntity productEntity = ProductMapper.toEntity(product);
        springDataProductRepository.save(productEntity);
    }
}
