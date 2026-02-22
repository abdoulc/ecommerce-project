package com.abdel.web.order;

import com.abdel.business.usecase.command.port.in.CreateOrderUseCase;
import com.abdel.business.usecase.command.port.in.CreatePaymentUseCase;
import com.abdel.business.usecase.input.CreateOrderInput;
import com.abdel.business.usecase.input.CreatePaymentInput;
import com.abdel.business.usecase.response.OrderResponseDTO;
import com.abdel.business.usecase.response.PaymentResponseDTO;
import com.abdel.web.generated.api.OrdersApiDelegate;
import com.abdel.web.generated.model.CreateOrderRequest;
import com.abdel.web.generated.model.OrderResponse;
import com.abdel.web.generated.model.PaymentRequest;
import com.abdel.web.generated.model.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrdersApiDelegateImpl implements OrdersApiDelegate {
    private final CreateOrderUseCase createOrderUseCase;
    private final CreatePaymentUseCase createPaymentUseCase;

    public OrdersApiDelegateImpl(CreateOrderUseCase createOrderUseCase, CreatePaymentUseCase createPaymentUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.createPaymentUseCase = createPaymentUseCase;
    }

    @Override
    public ResponseEntity<OrderResponse> createOrder(String idempotencyKey,
                                                      CreateOrderRequest createOrderRequest) {
        CreateOrderInput input = OrderApiMapper.toInput(idempotencyKey, createOrderRequest);
        OrderResponseDTO orderResponseDTO = createOrderUseCase.createOrder(input);
        return  ResponseEntity.ok(OrderApiMapper.toResponse(orderResponseDTO));

    }

    @Override
    public ResponseEntity<PaymentResponse> payOrder(String idempotencyKey,
                                                     String id,
                                                     PaymentRequest paymentRequest) {
        CreatePaymentInput createPaymentInput = OrderApiMapper.toCreatePaymentInput(idempotencyKey, id, paymentRequest);
        PaymentResponseDTO responseDTO= createPaymentUseCase.createPayment(createPaymentInput);

        return ResponseEntity.ok(new PaymentResponse().providerReference(responseDTO.providerReference())
                .paymentId(responseDTO.paymentId()));

    }
}
