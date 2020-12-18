package com.example.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class UserDetailsResponse {

    private User user;
    private String message;
    private HttpStatus httpStatus;
}
