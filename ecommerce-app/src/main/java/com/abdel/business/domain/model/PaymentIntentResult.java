package com.abdel.business.domain.model;

import com.abdel.business.domain.valueobject.ClientSecret;
import com.abdel.business.domain.valueobject.ProviderReference;

public record PaymentIntentResult(
        ProviderReference providerReference,
        ClientSecret clientSecret
) {}
