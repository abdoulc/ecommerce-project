package com.abdel.infrastructure.persistence.repository;

import com.abdel.infrastructure.persistence.entity.InventoryReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface SpringDataInventoryResRepository extends JpaRepository<InventoryReservationEntity, UUID> {
    @Query("""
        SELECT r FROM InventoryReservationEntity r
        WHERE r.status = 'RESERVED'
        AND r.expiryTime < :now
    """)
    List<InventoryReservationEntity> findExpiredReservations(LocalDateTime now);

    Optional<InventoryReservationEntity> findByProductId(String productId);
}
