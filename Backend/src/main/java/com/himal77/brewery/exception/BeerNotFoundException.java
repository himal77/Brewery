package com.himal77.brewery.exception;

public class BeerNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Beer Not Found!";
    }
}
