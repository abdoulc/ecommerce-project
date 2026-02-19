package com.abdel.business.usecase.response;

import com.abdel.business.domain.model.enums.OrderStatus;

import java.math.BigDecimal;

public record OrderResponseDTO(String orderId, OrderStatus status, BigDecimal totalAmount) {
}
