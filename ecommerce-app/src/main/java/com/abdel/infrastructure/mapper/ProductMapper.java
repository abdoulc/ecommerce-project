package com.abdel.infrastructure.mapper;


import com.abdel.business.domain.model.Product;
import com.abdel.infrastructure.persistence.entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(Product product) {
        return null;
       /* return new ProductEntity(
                product.getId().value(),
                product.getName().value(),
                product.getPrice().price(),
                product.isPublished()
        );*/
    }

    public static Product toDomain(ProductEntity entity) {
        return null;
        /*return Product.create(
                new ProductId(entity.getId()),
                new ProductName(entity.getName()),
                new Money(entity.getPrice())
        );*/
    }
}
