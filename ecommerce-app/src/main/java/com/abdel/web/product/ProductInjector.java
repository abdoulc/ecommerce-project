package com.abdel.web.product;

import com.abdel.business.usecase.command.impl.CreateProductUseCaseImpl;
import com.abdel.business.usecase.command.port.in.CreateProductUseCase;
import com.abdel.business.usecase.command.port.out.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductInjector {
    private final ProductRepository productRepository;

    public ProductInjector(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    CreateProductUseCase createProductUseCase(){
        return new CreateProductUseCaseImpl(productRepository);
    }
}
