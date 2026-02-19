package com.abdel.business.usecase.command.impl;

import com.abdel.business.domain.model.Order;
import com.abdel.business.domain.model.OrderItem;
import com.abdel.business.domain.model.enums.Currency;
import com.abdel.business.domain.model.enums.OrderStatus;
import com.abdel.business.domain.valueobject.Money;
import com.abdel.business.domain.valueobject.OrderId;
import com.abdel.business.usecase.command.port.in.CreateOrderUseCase;
import com.abdel.business.usecase.command.port.out.*;
import com.abdel.business.usecase.input.CreateOrderInput;
import com.abdel.business.usecase.response.OrderResponseDTO;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final IdempotencyPort idempotencyPort;
    private final InventoryRepository inventoryRepository;
    private final InventoryReservationRepository inventoryReservationRepository;
    private final OrderItemRepository orderItemRepository;

    public CreateOrderUseCaseImpl(OrderRepository orderRepository, IdempotencyPort idempotencyPort, InventoryRepository inventoryRepository, InventoryReservationRepository inventoryReservationRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.idempotencyPort = idempotencyPort;
        this.inventoryRepository = inventoryRepository;
        this.inventoryReservationRepository = inventoryReservationRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderResponseDTO createOrder(CreateOrderInput createOrderInput) {
         return idempotencyPort.execute(
                createOrderInput.idempotencyKey().value(),
                "CREATE_ORDER",
                Duration.ofMinutes(5),
                createOrderInput,
                () -> create(createOrderInput),
                 OrderResponseDTO.class
        );
    }

    private OrderResponseDTO create(CreateOrderInput createOrderInput) {
        OrderId orderId = OrderId.newId();
        processInventoryStock(createOrderInput.orderItems());
        processOrderInventoryReservation(orderId, createOrderInput.orderItems());
        Order order = Order.create(orderId, OrderStatus.PAYMENT_PENDING, calculateTotal(createOrderInput.orderItems()));
        orderRepository.save(order);
        saveOrderItems(orderId, createOrderInput.orderItems());
        return new OrderResponseDTO(order.getOrderId().value(), order.getStatus(), order.getTotalAmount().amount());
    }

    private void processOrderInventoryReservation(OrderId orderId, List<OrderItem> orderItems) {
        orderItems.forEach(orderItem -> inventoryReservationRepository.createInventoryReservation(
                orderId,
                orderItem
        ));
    }

    private void saveOrderItems(OrderId orderId, List<OrderItem> orderItems) {
        orderItems.forEach(orderItem -> orderItemRepository.save(orderId, orderItem));
    }

    private void processInventoryStock(List<OrderItem> orderItems) {
        orderItems.forEach(inventoryRepository::reserveInventory);
    }

    private Money calculateTotal(List<OrderItem> orderItems) {
        BigDecimal total = orderItems.stream()
                .map(item -> item.getPrice().amount().multiply(BigDecimal.valueOf(item.getQuantity().value())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Money(total, Currency.EUR);
    }
}
