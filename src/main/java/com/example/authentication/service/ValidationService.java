package com.example.authentication.service;

import com.example.authentication.model.User;

public interface ValidationService {

    boolean isUserValid(User user);
    boolean isNullOrEmpty(String string);
}
