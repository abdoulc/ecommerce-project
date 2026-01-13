package com.abdel.business.usecase.query.port.out;

import com.abdel.business.domain.model.ProductView;
import com.abdel.business.domain.valueobject.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductQueryPort {
    Optional<ProductView> findById(ProductId id);
    List<ProductView> findAll();
}
