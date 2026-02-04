package com.abdel.business.usecase.command.port.in;

import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.usecase.input.CreateProductInput;

public interface CreateProductUseCase {
    ProductId create(CreateProductInput input);
}
