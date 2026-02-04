package com.abdel.business.usecase.query.impl;

import com.abdel.business.domain.model.ProductView;
import com.abdel.business.usecase.query.port.in.GetAllProductsUseCase;
import com.abdel.business.usecase.query.port.out.ProductQueryPort;

import java.util.List;

public class GetAllProductsImpl implements GetAllProductsUseCase {
    private final ProductQueryPort productQueryPort;

    public GetAllProductsImpl(ProductQueryPort productQueryPort) {
        this.productQueryPort = productQueryPort;
    }

    @Override
    public List<ProductView> getAll() {
        return productQueryPort.findAll();
    }
}
