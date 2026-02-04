package com.abdel.web.product;


import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.usecase.command.port.in.CreateProductUseCase;
import com.abdel.business.usecase.input.CreateProductInput;

import com.abdel.web.generated.api.ProductsApiDelegate;
import com.abdel.web.generated.model.CreateProductRequest;
import com.abdel.web.generated.model.ProductResponse;
import com.abdel.web.mapper.ProductApiMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsApiDelegateImpl implements ProductsApiDelegate {
    private final CreateProductUseCase createProductUseCase;

    public ProductsApiDelegateImpl(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @Override
    public ResponseEntity<ProductResponse> createProduct(CreateProductRequest createProductRequest) {
        CreateProductInput input =
                ProductApiMapper.toInput(createProductRequest);

        ProductId productId = createProductUseCase.create(input);

        return ResponseEntity.ok(
                ProductApiMapper.toResponse(productId)
        );
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getProductList() {
      return ResponseEntity.ok(List.of(new ProductResponse().name("ppp")));
    }

}
