package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.Product;

public interface ProductRepository {
    void save(Product product);
}
