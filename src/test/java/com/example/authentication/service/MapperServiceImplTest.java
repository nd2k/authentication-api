package com.example.authentication.service;

import com.example.authentication.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class MapperServiceImplTest {

    private MapperServiceImpl mapperServiceImplTest;
    String userDtoTest = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"johndoe@gmail.com\", \"password\": \"test\" }";

    @BeforeEach
    void setUp() {
        mapperServiceImplTest = new MapperServiceImpl();
    }

    @Test
    void givenUserObject_whenMappingStringToObject_thenReturnMappedObject() throws JsonProcessingException {
        // Arrange
        User expectedUser = new User("John", "Doe", "johndoe@gmail.com", "test");
        // Act
        User userTest = mapperServiceImplTest.stringToUser(userDtoTest);
        // Assert
        assertEquals(expectedUser, userTest);
    }
}