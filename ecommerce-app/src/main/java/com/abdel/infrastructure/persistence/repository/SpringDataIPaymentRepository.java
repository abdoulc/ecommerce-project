package com.abdel.infrastructure.persistence.repository;

import com.abdel.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataIPaymentRepository extends JpaRepository<PaymentEntity, UUID> {

}
