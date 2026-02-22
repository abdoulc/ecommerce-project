package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.Payment;
import com.abdel.business.usecase.response.PaymentResponseDTO;

public interface PaymentRepository {

    void save(Payment payment);
}
