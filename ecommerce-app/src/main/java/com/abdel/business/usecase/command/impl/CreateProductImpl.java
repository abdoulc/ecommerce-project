package com.abdel.business.usecase.command.impl;

import com.abdel.business.domain.model.Product;
import com.abdel.business.usecase.command.port.in.CreateProductUseCase;
import com.abdel.business.usecase.command.port.out.ProductRepository;

public class CreateProductImpl implements CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }
}
