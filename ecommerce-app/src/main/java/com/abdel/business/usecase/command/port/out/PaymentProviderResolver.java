package com.abdel.business.usecase.command.port.out;

import com.abdel.business.domain.model.enums.ProviderType;

public interface PaymentProviderResolver {

    PaymentProvider resolve(ProviderType providerType);
}
