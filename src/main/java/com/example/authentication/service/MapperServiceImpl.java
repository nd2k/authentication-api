package com.example.authentication.service;

import com.example.authentication.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService {

    @Override
    public User stringToUser(String userDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(userDto, User.class);
    }
}
