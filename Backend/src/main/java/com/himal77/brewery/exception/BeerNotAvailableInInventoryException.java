package com.himal77.brewery.exception;

public class BeerNotAvailableInInventoryException extends RuntimeException  {
    private final String message;

    public BeerNotAvailableInInventoryException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
