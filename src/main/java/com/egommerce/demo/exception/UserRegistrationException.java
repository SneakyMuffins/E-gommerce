package com.egommerce.demo.exception;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String errorMessage) {
        super(errorMessage);
    }
}
