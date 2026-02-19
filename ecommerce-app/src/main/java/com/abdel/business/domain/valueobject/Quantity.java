package com.abdel.business.domain.valueobject;

public record Quantity(Integer value) {
    public Quantity {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

}
