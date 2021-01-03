package com.example.authentication.controller;

import com.example.authentication.model.User;
import com.example.authentication.model.UserDetailsResponse;
import com.example.authentication.service.MapperService;
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

    @Autowired
    UserController(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/signup")
    public ResponseEntity<UserDetailsResponse> register(@RequestBody String userDto) throws JsonProcessingException {
        User newUser = mapperService.stringToUser(userDto);
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(newUser, "User successfully registered", HttpStatus.CREATED);
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.CREATED);
    }
}
