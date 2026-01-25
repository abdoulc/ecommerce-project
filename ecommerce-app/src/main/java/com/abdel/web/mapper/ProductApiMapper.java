package com.abdel.web.mapper;

import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.usecase.input.CreateProductInput;
import com.abdel.web.generated.model.CreateProductRequest;
import com.abdel.web.generated.model.ProductResponse;


public class ProductApiMapper {

    private ProductApiMapper() {}

    public static CreateProductInput toInput(CreateProductRequest req) {
        return new CreateProductInput(
                req.getName(),
                req.getPrice()
        );
    }

    public static ProductResponse toResponse(ProductId id) {
        ProductResponse res = new ProductResponse();
        res.setId(id.value());
        return res;
    }

}

