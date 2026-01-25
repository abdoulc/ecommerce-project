package com.abdel.business.usecase.command.impl;

import com.abdel.business.domain.model.Product;
import com.abdel.business.domain.valueobject.Money;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.domain.valueobject.ProductName;
import com.abdel.business.usecase.command.port.in.CreateProductUseCase;
import com.abdel.business.usecase.command.port.out.ProductRepository;
import com.abdel.business.usecase.input.CreateProductInput;

public class CreateProductUseCaseImpl implements CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductId create(CreateProductInput productInput) {
        ProductId id = ProductId.newId();
        Product product = Product.create(
                id,
                new ProductName(productInput.name()),
                new Money(productInput.price())
        );
        productRepository.save(product);
        return id;
    }
}
