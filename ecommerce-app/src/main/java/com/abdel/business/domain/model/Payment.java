package com.abdel.business.domain.model;

import com.abdel.business.domain.model.enums.PaymentStatus;
import com.abdel.business.domain.valueobject.Money;
import com.abdel.business.domain.valueobject.OrderId;
import com.abdel.business.domain.valueobject.PaymentId;
import com.abdel.business.domain.valueobject.ProviderReference;

public class Payment {

    private final PaymentId paymentId;
    private final OrderId orderId;
    private PaymentStatus status;
    private final Money amount;
    private ProviderReference providerReference;

    private Payment(PaymentId paymentId,
                    OrderId orderId,
                    PaymentStatus status,
                    Money amount) {

        this.paymentId = paymentId;
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
    }

    public static Payment create(PaymentId paymentId,
                                 OrderId orderId,
                                 Money amount) {

        if (paymentId == null)
            throw new IllegalArgumentException("paymentId cannot be null");

        if (orderId == null)
            throw new IllegalArgumentException("orderId cannot be null");

        if (amount == null || amount.amount().signum() < 0)
            throw new IllegalArgumentException("amount cannot be negative");

        return new Payment(
                paymentId,
                orderId,
                PaymentStatus.PENDING,
                amount
        );
    }

    public void attachProviderPaymentId(ProviderReference providerReference) {
        if (providerReference == null)
            throw new IllegalArgumentException("providerReference cannot be null");

        if (this.providerReference != null)
            throw new IllegalStateException("providerReference already set");

        this.providerReference = providerReference;
    }

    public void markSucceeded() {
        if (status != PaymentStatus.PENDING)
            throw new IllegalStateException("Only pending payment can succeed");

        this.status = PaymentStatus.SUCCEEDED;
    }

    public void markFailed() {
        if (status != PaymentStatus.PENDING)
            throw new IllegalStateException("Only pending payment can fail");

        this.status = PaymentStatus.FAILED;
    }

    public void markExpired() {
        if (status != PaymentStatus.PENDING)
            throw new IllegalStateException("Only pending payment can expire");

        this.status = PaymentStatus.EXPIRED;
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Money getAmount() {
        return amount;
    }

    public ProviderReference getProviderReference() {
        return providerReference;
    }
}
