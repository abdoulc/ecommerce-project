package com.abdel.business.usecase.command.impl;

import com.abdel.business.domain.model.Order;
import com.abdel.business.domain.model.Payment;
import com.abdel.business.domain.model.enums.OrderStatus;
import com.abdel.business.domain.model.enums.ProviderType;
import com.abdel.business.domain.valueobject.PaymentId;
import com.abdel.business.domain.valueobject.ProviderReference;
import com.abdel.business.usecase.command.port.in.CreatePaymentUseCase;
import com.abdel.business.usecase.command.port.out.*;
import com.abdel.business.usecase.input.CreatePaymentInput;
import com.abdel.business.usecase.response.PaymentResponseDTO;

import java.time.Duration;

public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {
    private final IdempotencyPort idempotencyPort;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentProviderResolver paymentProviderResolver;

    public CreatePaymentUseCaseImpl(IdempotencyPort idempotencyPort, OrderRepository orderRepository, PaymentRepository paymentRepository, PaymentProviderResolver paymentProviderResolver) {
        this.idempotencyPort = idempotencyPort;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.paymentProviderResolver = paymentProviderResolver;
    }

    @Override
    public PaymentResponseDTO createPayment(CreatePaymentInput createPaymentInput) {
        return idempotencyPort.execute(
                createPaymentInput.idempotencyKey().value(),
                "CREATE_PAYMENT",
                Duration.ofMinutes(5),
                createPaymentInput,
                () -> handle(createPaymentInput),
                PaymentResponseDTO.class
        );    }

    private PaymentResponseDTO handle(CreatePaymentInput createPaymentInput) {
        Order order = orderRepository.getById(createPaymentInput.orderId());
        if(order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        validateNotCancelled(order);
        validateNotPaid(order);
        createPaymentIntent(order);
       return savePayment(order, new ProviderReference("stripe intent id"));
    }

    private void createPaymentIntent(Order order) {
        PaymentProvider provider = paymentProviderResolver.resolve(ProviderType.STRIPE);
        provider.createPaymentIntent(order);
    }

    private PaymentResponseDTO savePayment(Order order, ProviderReference providerReference){
        try {
            PaymentId paymentId = PaymentId.newId();
            Payment payment = Payment.create(paymentId, order.getOrderId(), order.getTotalAmount());
            ProviderReference p= new ProviderReference("stripe intent id");
            payment.attachProviderPaymentId(providerReference);
            paymentRepository.save(payment);
            return new PaymentResponseDTO(
                    p.value(),
                    paymentId.value());

        }catch (Exception e) {
            //stripe.paymentIntents().cancel(intent.getId());
            throw new RuntimeException("Failed to create payment intent", e);
        }

    }

    private void validateNotPaid(Order order) {
        if(OrderStatus.PAID==order.getStatus()) {
            throw new IllegalStateException("Order is already paid");
        }
    }

    private void validateNotCancelled(Order order) {
            if(OrderStatus.CANCELLED==order.getStatus()) {
                throw new IllegalStateException("Order is cancelled");
            }
    }
}
