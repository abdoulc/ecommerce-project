package com.abdel.business.usecase.command.port.in;

import com.abdel.business.usecase.input.CreatePaymentInput;
import com.abdel.business.usecase.response.PaymentResponseDTO;

public interface CreatePaymentUseCase {
     PaymentResponseDTO createPayment(CreatePaymentInput createPaymentInput);
}
