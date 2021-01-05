package com.example.authentication.exception;

public class NullOrEmptyUserDtoException extends RuntimeException {
    public NullOrEmptyUserDtoException(String errorMessage) {
        super(errorMessage);
    }
}
