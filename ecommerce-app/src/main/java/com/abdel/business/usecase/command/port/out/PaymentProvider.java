package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.Order;
import com.abdel.business.domain.model.PaymentIntentResult;
import com.abdel.business.domain.model.enums.ProviderType;

public interface PaymentProvider {

    PaymentIntentResult createPaymentIntent(Order order);

    void handleWebhook(String payload, String signature);

    ProviderType getType();
}
