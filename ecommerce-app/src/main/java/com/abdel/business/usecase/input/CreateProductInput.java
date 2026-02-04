package com.abdel.business.usecase.input;

import java.math.BigDecimal;

public record CreateProductInput(
        String name,
        BigDecimal price
) {}
