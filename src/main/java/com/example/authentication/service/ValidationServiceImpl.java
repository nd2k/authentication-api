package com.example.authentication.service;

import com.example.authentication.exception.NullOrEmptyUserDtoException;
import com.example.authentication.exception.InvalidEmailException;
import com.example.authentication.model.User;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isUserValid(User user) {
        if (isNullOrEmpty(user.getFirstName()))
            throw new NullOrEmptyUserDtoException("FirstName is a mandatory field");
        if (isNullOrEmpty(user.getLastName()))
            throw new NullOrEmptyUserDtoException("LastName is a mandatory field");
        if (isNullOrEmpty(user.getEmail()))
            throw new NullOrEmptyUserDtoException("Email is a mandatory field");
        if (isNullOrEmpty(user.getPassword()))
            throw new NullOrEmptyUserDtoException("Password is a mandatory field");
        if (!isEmailValid(user.getEmail()))
            throw new InvalidEmailException(user.getEmail());
        return true;
    }

    private boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    @Override
    public boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}


