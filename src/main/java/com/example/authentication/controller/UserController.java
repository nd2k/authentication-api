package com.example.authentication.controller;

import com.example.authentication.model.User;
import com.example.authentication.model.UserDetailsResponse;
import com.example.authentication.service.MapperService;
import com.example.authentication.service.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/v1/user")
@Slf4j
public class UserController {

    private final MapperService mapperService;
    private final ValidationService validationService;

    @Autowired
    UserController(MapperService mapperService, ValidationService validationService) {
        this.mapperService = mapperService;
        this.validationService = validationService;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/signup")
    public ResponseEntity<UserDetailsResponse> register(@RequestBody String userDto) throws JsonProcessingException {
        User newUser = mapperService.stringToUser(userDto);
        validationService.isUserValid(newUser);
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(newUser, "User successfully sign up", HttpStatus.CREATED);
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.CREATED);
    }
}
