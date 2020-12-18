package com.example.authentication.controller;

import com.example.authentication.model.User;
import com.example.authentication.model.UserDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class UserControllerTest {

    private MockMvc mockMvc;
    private UserController userControllerTest;
    String userDto;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        userControllerTest = new UserController();
        userDto = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"johndoe@gmail.com\", \"password\": \"test\" }";
    }

    @Test
    void givenPostValidUrl_whenUserRegister_thenHttp201Response() throws Exception {
        // Arrange, Act & assert
        this.mockMvc.perform(post("/auth/v1/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDto))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void givenPostValidRequest_whenUserNotCreated_thenException() throws Exception {
        // Arrange
        User expectedUser = new User("John", "Doe", "johndoe@gmail.com", "test");
        UserDetailsResponse expectedUserDetailsResponse = new UserDetailsResponse(expectedUser, "User successfully created", HttpStatus.CREATED);
        ResponseEntity expectedResponseEntity = new ResponseEntity(expectedUserDetailsResponse, HttpStatus.CREATED);

        // Act
        ResponseEntity<UserDetailsResponse> response = userControllerTest.register(userDto);
        // Assert
        assertEquals(expectedResponseEntity, response);

    }

    @Test
    void givenPostInvalidRequest_whenUserNotCreated_thenException() throws Exception {
        // Arrange
        userDto = "{ \"wrongField\": \"\"}";
        // Act & assert
        assertThrows(Exception.class, () -> userControllerTest.register(userDto), "Conversion to User class failed");
    }
}