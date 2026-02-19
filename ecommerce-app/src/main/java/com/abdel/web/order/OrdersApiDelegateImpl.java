package com.abdel.web.order;

import com.abdel.business.usecase.command.port.in.CreateOrderUseCase;
import com.abdel.business.usecase.input.CreateOrderInput;
import com.abdel.business.usecase.response.OrderResponseDTO;
import com.abdel.web.generated.api.OrdersApiDelegate;
import com.abdel.web.generated.model.CreateOrderRequest;
import com.abdel.web.generated.model.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrdersApiDelegateImpl implements OrdersApiDelegate {
    private final CreateOrderUseCase createOrderUseCase;

    public OrdersApiDelegateImpl(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @Override
    public ResponseEntity<OrderResponse> createOrder(String idempotencyKey,
                                                      CreateOrderRequest createOrderRequest) {
        CreateOrderInput input = OrderApiMapper.toInput(idempotencyKey, createOrderRequest);
        OrderResponseDTO orderResponseDTO = createOrderUseCase.createOrder(input);
        return  ResponseEntity.ok(OrderApiMapper.toResponse(orderResponseDTO));

    }
}
