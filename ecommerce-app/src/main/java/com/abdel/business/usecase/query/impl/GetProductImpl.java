package com.abdel.business.usecase.query.impl;

import com.abdel.business.domain.model.ProductView;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.usecase.query.port.in.GetProductUseCase;
import com.abdel.business.usecase.query.port.out.ProductQueryPort;

import java.util.Optional;

public class GetProductImpl implements GetProductUseCase {
    private final ProductQueryPort productQueryPort;

    public GetProductImpl(ProductQueryPort productQueryPort) {
        this.productQueryPort = productQueryPort;
    }

    @Override
    public Optional<ProductView> getById(ProductId productId) {
        return productQueryPort.findById(productId);
    }
}
