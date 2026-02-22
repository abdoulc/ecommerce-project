package com.abdel.infrastructure.adapter;

import com.abdel.business.domain.model.Order;
import com.abdel.business.domain.model.PaymentIntentResult;
import com.abdel.business.domain.model.enums.ProviderType;
import com.abdel.business.usecase.command.port.out.PaymentProvider;
import org.springframework.stereotype.Component;

@Component
public class StripeProvider implements PaymentProvider {


    @Override
    public PaymentIntentResult createPaymentIntent(Order order) {
        /*PaymentIntent intent = stripeClient.createIntent(
                order.getTotalAmount(),
                order.getCurrency(),
                order.getId()
        );

        return new PaymentIntentResult(
                intent.getId(),
                intent.getClientSecret()
        );*/
        return null;
    }

    @Override
    public void handleWebhook(String payload, String signature) {

    }

    @Override
    public ProviderType getType() {
        return null;
    }
}
