package com.abdel.business.usecase.command.port.out;

import java.time.Duration;
import java.util.function.Supplier;

public interface IdempotencyPort {
    <T, R> T execute(String key, String operation, Duration ttl, R payload, Supplier<T> action, Class<T> responseType);

}
