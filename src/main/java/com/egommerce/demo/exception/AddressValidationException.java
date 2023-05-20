package com.egommerce.demo.exception;

public class AddressValidationException extends RuntimeException {
    public AddressValidationException(String errorMessage) {
        super(errorMessage);
    }
}
