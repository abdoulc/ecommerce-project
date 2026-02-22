package com.abdel.infrastructure.adapter;

import com.abdel.business.domain.model.enums.ProviderType;
import com.abdel.business.usecase.command.port.out.PaymentProvider;
import com.abdel.business.usecase.command.port.out.PaymentProviderResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentProviderFactory implements PaymentProviderResolver {

    private final Map<ProviderType, PaymentProvider> providers;

    public PaymentProviderFactory(List<PaymentProvider> providerList) {
        this.providers = providerList.stream()
                .collect(Collectors.toMap(
                        PaymentProvider::getType,
                        Function.identity()
                ));
    }

    @Override
    public PaymentProvider resolve(ProviderType providerType) {
        return Optional.ofNullable(providers.get(providerType))
                .orElseThrow(() ->
                        new IllegalArgumentException("Unsupported provider"));
    }
}
