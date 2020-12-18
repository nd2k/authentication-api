package com.example.authentication.controller;

import com.example.authentication.model.User;
import com.example.authentication.model.UserDetailsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/v1/user")
@Slf4j
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<UserDetailsResponse> register(@RequestBody String userDto) throws Exception {
        ObjectMapper userMapper = new ObjectMapper();
        User newUser = null;
        try {
            newUser = userMapper.readValue(userDto, User.class);
        } catch (Exception e) {
            log.error("Conversion to User class failed: {}", e);
            throw new Exception("Conversion to User class failed");
            // TODO: improve exception handling
        }
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(newUser, "User successfully created", HttpStatus.CREATED);
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.CREATED);
    }
}
