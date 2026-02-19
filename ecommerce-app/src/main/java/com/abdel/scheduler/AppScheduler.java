package com.abdel.scheduler;

import com.abdel.business.domain.model.enums.ReservationStatus;
import com.abdel.business.domain.valueobject.ProductId;
import com.abdel.business.domain.valueobject.Quantity;
import com.abdel.business.usecase.command.port.out.InventoryRepository;
import com.abdel.infrastructure.persistence.entity.InventoryEntity;
import com.abdel.infrastructure.persistence.entity.InventoryReservationEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataInventoryRepository;
import com.abdel.infrastructure.persistence.repository.SpringDataInventoryResRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AppScheduler {
    private final SpringDataInventoryResRepository reservationRepository;
    private final InventoryRepository inventoryRepository;

    public AppScheduler(SpringDataInventoryResRepository reservationRepository, InventoryRepository inventoryRepository) {
        this.reservationRepository = reservationRepository;
        this.inventoryRepository = inventoryRepository;
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
        }
    }

}
