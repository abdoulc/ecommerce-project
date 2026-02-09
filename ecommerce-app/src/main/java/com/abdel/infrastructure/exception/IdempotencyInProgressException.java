package com.abdel.infrastructure.exception;

public class IdempotencyInProgressException extends RuntimeException {
    public IdempotencyInProgressException(String key) {
        super("Operation in progress for key: " + key);
    }
}
