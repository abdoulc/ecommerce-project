package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.Product;
import com.abdel.business.domain.model.ProductView;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.usecase.command.port.out.ProductRepository;
import com.abdel.business.usecase.query.port.out.ProductQueryPort;
import com.abdel.infrastructure.mapper.ProductMapper;
import com.abdel.infrastructure.persistence.entity.ProductEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProductRepository implements ProductRepository, ProductQueryPort {
    private final SpringDataProductRepository springDataProductRepository;

    public JpaProductRepository(SpringDataProductRepository springDataProductRepository) {
        this.springDataProductRepository = springDataProductRepository;
    }

    @Override
    public void save(Product product) {
        ProductEntity productEntity = ProductMapper.toEntity(product);
        springDataProductRepository.save(productEntity);
    }

    @Override
    public Optional<ProductView> findById(ProductId id) {
        return Optional.empty();
    }

    @Override
    public List<ProductView> findAll() {
        return null;
    }
}
