package com.abdel.business.domain.valueobject;

import java.util.UUID;
import java.util.function.Function;

public final class IdFactory {

    private IdFactory() {
    }

    public static <T> T newId(Function<String, T> creator) {
        return creator.apply(UUID.randomUUID().toString());
    }
}