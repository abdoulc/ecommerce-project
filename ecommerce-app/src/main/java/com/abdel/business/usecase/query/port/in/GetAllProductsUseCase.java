package com.abdel.business.usecase.query.port.in;

import com.abdel.business.domain.model.ProductView;

import java.util.List;

public interface GetAllProductsUseCase {
    List<ProductView> getAll();
}
