package com.abdel.business.domain.valueobject;

import java.math.BigDecimal;

public record Money(BigDecimal price) {
    public Money{
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money amount must be non-negative");
        }
    }

    public boolean isNegative(){
        return price.compareTo(BigDecimal.ZERO) < 0;
    }
}
