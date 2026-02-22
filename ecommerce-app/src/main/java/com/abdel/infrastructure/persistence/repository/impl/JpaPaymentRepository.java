package com.abdel.infrastructure.persistence.repository.impl;

import com.abdel.business.domain.model.Payment;
import com.abdel.business.usecase.command.port.out.PaymentRepository;
import com.abdel.infrastructure.persistence.entity.PaymentEntity;
import com.abdel.infrastructure.persistence.repository.SpringDataIPaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPaymentRepository implements PaymentRepository {
    private final SpringDataIPaymentRepository springDataIPaymentRepository;

    public JpaPaymentRepository(SpringDataIPaymentRepository springDataIPaymentRepository) {
        this.springDataIPaymentRepository = springDataIPaymentRepository;
    }

    @Override
    @Transactional
    public void save(Payment payment) {
      PaymentEntity paymentEntity = new PaymentEntity();
      paymentEntity.setId(payment.getPaymentId().value());
      paymentEntity.setOrderId(payment.getOrderId().value());
      paymentEntity.setAmount(payment.getAmount().amount());
        paymentEntity.setStatus(payment.getStatus());
      paymentEntity.setCurrency(payment.getAmount().currency().name());
      paymentEntity.setProvider("");
      paymentEntity.setProviderReference(payment.getProviderReference().value());
      springDataIPaymentRepository.save(paymentEntity);
    }
}
