package com.abdel.infrastructure.exception;

public class IdempotencyConflictException extends RuntimeException {
    public IdempotencyConflictException(String key) {
        super("Conflict for key: " + key);
    }
}

