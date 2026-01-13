package com.abdel.business.domain.valueobject;

public record ProductName(String value) {
    public ProductName{
        if(value==null || value.isBlank()){
            throw new IllegalArgumentException("ProductName cannot be blank");
        }
    }
}
