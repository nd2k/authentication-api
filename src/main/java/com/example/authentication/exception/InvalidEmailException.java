package com.example.authentication.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super(email + " address is not a valid email address");
    }
}
