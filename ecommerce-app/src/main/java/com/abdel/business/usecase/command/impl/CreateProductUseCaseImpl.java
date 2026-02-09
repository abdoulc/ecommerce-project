package com.abdel.business.usecase.command.impl;

import com.abdel.business.domain.model.Product;
import com.abdel.business.domain.valueobject.Money;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.domain.valueobject.ProductName;
import com.abdel.business.usecase.command.port.in.CreateProductUseCase;
import com.abdel.business.usecase.command.port.out.IdempotencyPort;
import com.abdel.business.usecase.command.port.out.ProductRepository;
import com.abdel.business.usecase.input.CreateProductInput;
import com.abdel.infrastructure.adapter.IdempotencyDatabaseAdapter;

import java.time.Duration;
import java.util.UUID;

public class CreateProductUseCaseImpl implements CreateProductUseCase {
    private final ProductRepository productRepository;
    private final IdempotencyPort idempotencyPort;

    public CreateProductUseCaseImpl(ProductRepository productRepository, IdempotencyPort idempotencyPort) {
        this.productRepository = productRepository;
        this.idempotencyPort = idempotencyPort;
    }

    @Override
    public ProductId create(CreateProductInput productInput) {

        return idempotencyPort.execute(
               "customKey",
                "CREATE_PRODUCT",
                Duration.ofMinutes(5),
                productInput,
                () -> saveProduct(productInput),
                ProductId.class
        );
    }

    private ProductId saveProduct(CreateProductInput productInput) {
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
