package com.himal77.brewery.exception;

public class InventoryFullException extends RuntimeException {
    private final String message;

    public InventoryFullException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
