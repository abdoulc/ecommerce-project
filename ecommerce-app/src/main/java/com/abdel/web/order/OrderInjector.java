package com.abdel.web.order;

import com.abdel.business.usecase.command.impl.CreateOrderUseCaseImpl;
import com.abdel.business.usecase.command.impl.CreatePaymentUseCaseImpl;
import com.abdel.business.usecase.command.port.in.CreateOrderUseCase;
import com.abdel.business.usecase.command.port.in.CreatePaymentUseCase;
import com.abdel.business.usecase.command.port.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderInjector {
    private final OrderRepository orderRepository;
    private final IdempotencyPort idempotencyPort;
    private final InventoryRepository inventoryRepository;
    private final InventoryReservationRepository inventoryReservationRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;

    public OrderInjector(OrderRepository orderRepository, IdempotencyPort idempotencyPort, InventoryRepository inventoryRepository, InventoryReservationRepository inventoryReservationRepository, OrderItemRepository orderItemRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.idempotencyPort = idempotencyPort;
        this.inventoryRepository = inventoryRepository;
        this.inventoryReservationRepository = inventoryReservationRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentRepository = paymentRepository;
    }

    @Bean
    CreateOrderUseCase createOrderUseCase(){
        return new CreateOrderUseCaseImpl(orderRepository, idempotencyPort, inventoryRepository, inventoryReservationRepository, orderItemRepository);
    }

    @Bean
    CreatePaymentUseCase createPaymentUseCase(){
        return new CreatePaymentUseCaseImpl(idempotencyPort, orderRepository, paymentRepository);
    }
}
