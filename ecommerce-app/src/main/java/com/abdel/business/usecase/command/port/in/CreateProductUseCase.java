package com.abdel.business.usecase.command.port.in;

import com.abdel.business.domain.model.Product;

public interface CreateProductUseCase {
    void createProduct(Product product);
}
