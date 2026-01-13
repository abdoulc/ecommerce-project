package com.abdel.business.usecase.query.port.in;

import com.abdel.business.domain.model.ProductView;
import com.abdel.business.domain.valueobject.ProductId;

import java.util.Optional;

public interface GetProductUseCase {
    Optional<ProductView> getById(ProductId productId);
}
