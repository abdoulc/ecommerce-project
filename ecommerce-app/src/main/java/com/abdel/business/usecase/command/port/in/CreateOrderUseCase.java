package com.abdel.business.usecase.command.port.in;

import com.abdel.business.usecase.input.CreateOrderInput;
import com.abdel.business.usecase.response.OrderResponseDTO;

public interface CreateOrderUseCase {
     OrderResponseDTO createOrder(CreateOrderInput createOrderInput);
}
