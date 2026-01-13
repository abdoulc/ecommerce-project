package com.abdel.business.domain.model;

import com.abdel.business.domain.valueobject.Money;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.domain.valueobject.ProductName;

public class Product {

    private final ProductId id;
    private ProductName name;
    private Money price;
    private boolean published;

    private Product(ProductId id, ProductName name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.published = false;
    }

    public static Product create(ProductId id, ProductName name, Money price) {
        if (name == null || name.value().isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        if (price == null || price.isNegative()) throw new IllegalArgumentException("Price cannot be negative");
        return new Product(id, name, price);
    }

    // Behaviors
    //public void rename(ProductName newName) { ... }
   // public void changePrice(Money newPrice) { ... }
    public void publish() { published = true; }

    // Getters
    public ProductId getId() { return id; }
    public ProductName getName() { return name; }
    public Money getPrice() { return price; }
    public boolean isPublished() { return published; }
}

