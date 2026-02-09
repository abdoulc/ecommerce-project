package com.abdel.web.product;

import com.abdel.business.usecase.command.impl.CreateProductUseCaseImpl;
import com.abdel.business.usecase.command.port.in.CreateProductUseCase;
import com.abdel.business.usecase.command.port.out.IdempotencyPort;
import com.abdel.business.usecase.command.port.out.ProductRepository;
import com.abdel.infrastructure.adapter.IdempotencyDatabaseAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductInjector {
    private final ProductRepository productRepository;
    private final IdempotencyPort idempotencyPort;

    public ProductInjector(ProductRepository productRepository, IdempotencyPort idempotencyPort) {
        this.productRepository = productRepository;
        this.idempotencyPort = idempotencyPort;
    }

    @Bean
    CreateProductUseCase createProductUseCase(){
        return new CreateProductUseCaseImpl(productRepository, idempotencyPort);
    }

}
