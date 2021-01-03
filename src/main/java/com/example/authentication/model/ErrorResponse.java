package com.example.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String errorMessage;
}
