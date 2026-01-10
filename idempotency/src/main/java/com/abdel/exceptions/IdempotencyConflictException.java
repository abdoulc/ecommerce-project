package com.abdel.exceptions;

public class IdempotencyConflictException extends RuntimeException {
    public IdempotencyConflictException(String key) {
        super("Conflict for key: " + key);
    }
}

