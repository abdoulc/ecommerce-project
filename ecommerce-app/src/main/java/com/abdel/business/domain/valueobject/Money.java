package com.abdel.business.domain.valueobject;

import com.abdel.business.domain.model.enums.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, Currency currency) {
    public Money{
        Objects.requireNonNull(currency, "currency must not be null");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money amount must be non-negative");
        }
    }

    public boolean isNegative(){
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }

    public static Money of(BigDecimal amount, Currency currencyCode) {
        return new Money(amount, currencyCode);
    }

    public Money add(Money other) {
        validateCurrency(other);
        return new Money(this.amount.add(other.amount), currency);
    }

    public Money subtract(Money other) {
        validateCurrency(other);
        return new Money(this.amount.subtract(other.amount), currency);
    }

    public Money multiply(int factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)), currency);
    }

    private void validateCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot operate on different currencies");
        }
    }
}
