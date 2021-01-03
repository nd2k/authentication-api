package com.example.authentication.exception;

import com.example.authentication.model.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandlerTest;

    @BeforeEach
    void setUp() {
        customExceptionHandlerTest = new CustomExceptionHandler();
    }

    @Test
    void givenInvalidString_whenMappingStringToObject_thenException() {
        // Arrange
        ErrorResponse expectedErrorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Your JSON request is not properly formatted");
        ResponseEntity<ErrorResponse> expectedResponseEntity = new ResponseEntity<>(expectedErrorResponse, HttpStatus.BAD_REQUEST);
        JsonProcessingException jsonProcessingExceptionTest = new JsonProcessingException(""){};
        // Act
        ResponseEntity<ErrorResponse> responseEntityTest = customExceptionHandlerTest.handleUserMappingException(jsonProcessingExceptionTest);
        // Assert
        assertEquals(expectedResponseEntity, responseEntityTest);
    }
}