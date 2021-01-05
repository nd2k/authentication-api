package com.example.authentication.controller;

import com.example.authentication.model.User;
import com.example.authentication.model.UserDetailsResponse;
import com.example.authentication.service.MapperServiceImpl;
import com.example.authentication.service.ValidationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private UserController userControllerTest;
    @Mock
    private MapperServiceImpl mapperServiceImplTest;
    @Mock
    private ValidationServiceImpl validationServiceImplTest;
    String userDtoTest = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"johndoe@gmail.com\", \"password\": \"test\" }";
    User expectedUser;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(mapperServiceImplTest, validationServiceImplTest)).build();
        userControllerTest = new UserController(mapperServiceImplTest, validationServiceImplTest);
    }

    @Test
    void givenPostHttpRequest_whenUrlIsValid_thenHttp201Response() throws Exception {
        // Arrange, Act & assert
        this.mockMvc.perform(post("/auth/v1/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoTest))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void givenPostValidRequest_whenRegisterUser_thenHttp201Response() throws Exception {
        // Arrange
        expectedUser = new User("John", "Doe", "johndoe@gmail.com", "test");
        when(mapperServiceImplTest.stringToUser(userDtoTest)).thenReturn(expectedUser);
        UserDetailsResponse expectedUserDetailsResponse = new UserDetailsResponse(expectedUser, "User successfully sign up", HttpStatus.CREATED);
        ResponseEntity<UserDetailsResponse> expectedResponseEntity = new ResponseEntity<>(expectedUserDetailsResponse, HttpStatus.CREATED);
        // Act
        ResponseEntity<UserDetailsResponse> response = userControllerTest.register(userDtoTest);
        // Assert
        assertEquals(expectedResponseEntity, response);
    }
}