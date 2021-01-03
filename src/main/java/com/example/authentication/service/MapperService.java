package com.example.authentication.service;

import com.example.authentication.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MapperService {
    User stringToUser(String userDto) throws JsonProcessingException;
}
