package com.abdel.business.domain.model;

import com.abdel.business.domain.valueobject.Money;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.domain.valueobject.Quantity;

public class OrderItem {
    private final ProductId ProductId;
    private final Quantity quantity;

    private final Money price;


    public OrderItem(ProductId productId, Quantity quantity, Money price) {
        ProductId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItem create(ProductId productId, Quantity quantity, Money price) {
        if (productId == null || productId.value().isBlank()) throw new IllegalArgumentException("productId cannot be blank");
        if (quantity == null || quantity.value()<0) throw new IllegalArgumentException("Price cannot be negative");
        return new OrderItem(productId, quantity, price);
    }

    public ProductId getProductId() {
        return ProductId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }
}
