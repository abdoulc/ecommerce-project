package com.abdel.business.domain.model;

import com.abdel.business.domain.model.enums.OrderStatus;
import com.abdel.business.domain.valueobject.Money;
import com.abdel.business.domain.valueobject.OrderId;

import java.math.BigDecimal;

public class Order {
    private OrderId orderId;
    private OrderStatus status;
    private Money totalAmount;

    public Order( OrderId orderId,OrderStatus status, Money totalAmount) {
        this.status = status;
        this.totalAmount = totalAmount;
            this.orderId = orderId;
    }


    public static Order create( OrderId orderId, OrderStatus status, Money money) {
        if (status == null) throw new IllegalArgumentException("status cannot be null");
        if (money == null || money.amount().compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("totalAmount cannot be negative");
        return new Order(orderId, status, money);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public OrderId getOrderId() {
        return orderId;
    }

}
