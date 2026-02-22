package com.abdel.scheduler;

import com.abdel.business.domain.model.enums.ReservationStatus;
import com.abdel.business.domain.valueobject.OrderId;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.domain.valueobject.Quantity;
import com.abdel.business.usecase.command.port.out.InventoryRepository;
import com.abdel.business.usecase.command.port.out.OrderRepository;
import com.abdel.infrastructure.persistence.entity.InventoryReservationEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataInventoryResRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AppScheduler {
    private final SpringDataInventoryResRepository reservationRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderRepository orderRepository;

    public AppScheduler(SpringDataInventoryResRepository reservationRepository, InventoryRepository inventoryRepository, OrderRepository orderRepository) {
        this.reservationRepository = reservationRepository;
        this.inventoryRepository = inventoryRepository;
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void expireReservations() {

        List<InventoryReservationEntity> expired =
                reservationRepository.findExpiredReservations(
                        LocalDateTime.now()
                );

        for (InventoryReservationEntity r : expired) {
            r.setStatus(ReservationStatus.EXPIRED);
            inventoryRepository.releaseInventory(new ProductId(r.getProductId()),new Quantity(r.getQuantity()));
            reservationRepository.save(r);
            cancelOrder(r.getOrderId());
            markPaymentExpired(r.getOrderId());
        }
    }

    private void markPaymentExpired(@NonNull String orderId) {

    }

    private void cancelOrder(@NonNull String orderId) {
        orderRepository.cancerlOrder(new OrderId(orderId));
    }

}
