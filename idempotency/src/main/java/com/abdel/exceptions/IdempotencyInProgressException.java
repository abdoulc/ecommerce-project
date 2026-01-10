package com.abdel.exceptions;

public class IdempotencyInProgressException extends RuntimeException {
    public IdempotencyInProgressException(String key) {
        super("Operation in progress for key: " + key);
    }
}
