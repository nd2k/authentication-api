package com.example.authentication.service;

import com.example.authentication.exception.InvalidEmailException;
import com.example.authentication.exception.NullOrEmptyUserDtoException;
import com.example.authentication.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ValidationServiceImplTest {

    private ValidationService validationServiceTest;
    User userTest;
    String expectedExceptionMessage;

    @BeforeEach
    void setUp() {
        validationServiceTest = new ValidationServiceImpl();
    }

    @Test
    void GivenFirstNameIsEmpty_whenValidateUser_thenNullOrEmptyUserDtoException() {
        // Arrange
        userTest = new User("", "Doe", "johndoe@gmail.com", "test");
        expectedExceptionMessage = "FirstName is a mandatory field";
        // Act
        NullOrEmptyUserDtoException nullOrEmptyUserDtoException = assertThrows(NullOrEmptyUserDtoException.class, () -> validationServiceTest.isUserValid(userTest));
        // Assert
        assertEquals(expectedExceptionMessage, nullOrEmptyUserDtoException.getMessage());
    }

    @Test
    void GivenLastNameIsEmpty_whenValidateUser_thenNullOrEmptyUserDtoException() {
        // Arrange
        userTest = new User("John", "", "johndoe@gmail.com", "test");
        expectedExceptionMessage = "LastName is a mandatory field";
        // Act
        NullOrEmptyUserDtoException nullOrEmptyUserDtoException = assertThrows(NullOrEmptyUserDtoException.class, () -> validationServiceTest.isUserValid(userTest));
        // Assert
        assertEquals(expectedExceptionMessage, nullOrEmptyUserDtoException.getMessage());
    }

    @Test
    void GivenEmailIsEmpty_whenValidateUser_thenNullOrEmptyUserDtoException() {
        // Arrange
        userTest = new User("John", "Doe", "", "test");
        expectedExceptionMessage = "Email is a mandatory field";
        // Act
        NullOrEmptyUserDtoException nullOrEmptyUserDtoException = assertThrows(NullOrEmptyUserDtoException.class, () -> validationServiceTest.isUserValid(userTest));
        // Assert
        assertEquals(expectedExceptionMessage, nullOrEmptyUserDtoException.getMessage());
    }

    @Test
    void GivenPasswordIsEmpty_whenValidateUser_thenNullOrEmptyUserDtoException() {
        // Arrange
        userTest = new User("John", "Doe", "johndoe@gmail.com", "");
        expectedExceptionMessage = "Password is a mandatory field";
        // Act
        NullOrEmptyUserDtoException nullOrEmptyUserDtoException = assertThrows(NullOrEmptyUserDtoException.class, () -> validationServiceTest.isUserValid(userTest));
        // Assert
        assertEquals(expectedExceptionMessage, nullOrEmptyUserDtoException.getMessage());
    }

    @Test
    void GivenEmailIsInvalid_whenValidateUser_thenInvalidEmailException() {
        // Arrange
        userTest = new User("John", "Doe", "john.com", "test");
        expectedExceptionMessage = "john.com address is not a valid email address";
        // Act
        InvalidEmailException invalidEmailException = assertThrows(InvalidEmailException.class, () -> validationServiceTest.isUserValid(userTest));
        // Assert
        assertEquals(expectedExceptionMessage, invalidEmailException.getMessage());
    }

    @Test
    void GivenUserIsValid_whenValidateUser_thenInvalidEmailException() {
        // Arrange
        userTest = new User("John", "Doe", "johndoe@gmail.com", "test");
        // Act & Assert
        assertTrue(validationServiceTest.isUserValid(userTest));
    }

    @Test
    void GivenFieldIsEmpty_whenValidateUser_thenTrue() {
        // Assert
        assertTrue(validationServiceTest.isNullOrEmpty(""));
    }

    @Test
    void GivenFieldIsNull_whenValidateUser_thenTrue() {
        // Assert
        assertTrue(validationServiceTest.isNullOrEmpty(null));
    }

}