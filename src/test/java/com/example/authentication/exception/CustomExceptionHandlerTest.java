package com.example.authentication.exception;

import com.example.authentication.model.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandlerTest;
    static String MESSAGE = " is a mandatory field";

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

    @ParameterizedTest
    @ValueSource(strings = {"FirstName", "LastName", "Email", "Password"})
    void givenOneUserFieldIsEmpty_whenMappingStringToObject_thenException(String field) {
        // Arrange
        ErrorResponse expectedErrorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, field + MESSAGE);
        ResponseEntity<ErrorResponse> expectedResponseEntity = new ResponseEntity<>(expectedErrorResponse, HttpStatus.BAD_REQUEST);
        NullOrEmptyUserDtoException nullOrEmptyUserDtoExceptionTest = new NullOrEmptyUserDtoException(field + MESSAGE);
        // Act
        ResponseEntity<ErrorResponse> responseEntityTest = customExceptionHandlerTest.handleNullOrEmptyDtoException(nullOrEmptyUserDtoExceptionTest);
        // Assert
        assertEquals(expectedResponseEntity, responseEntityTest);
    }

    @Test
    void givenInvalidEmail_whenMappingStringToObject_thenException() {
        // Arrange
        ErrorResponse expectedErrorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "wrongEmail address is not a valid email address");
        ResponseEntity<ErrorResponse> expectedResponseEntity = new ResponseEntity<>(expectedErrorResponse, HttpStatus.BAD_REQUEST);
        InvalidEmailException invalidEmailExceptionTest = new InvalidEmailException("wrongEmail");
        // Act
        ResponseEntity<ErrorResponse> responseEntityTest = customExceptionHandlerTest.handleInvalidEmailException(invalidEmailExceptionTest);
        // Assert
        assertEquals(expectedResponseEntity, responseEntityTest);
    }
}