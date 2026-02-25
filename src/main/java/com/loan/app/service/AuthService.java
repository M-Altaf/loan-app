package com.loan.app.service;

import com.loan.app.rest.dto.TokenDtos.UserDto;

import java.util.List;

public interface AuthService {
    /**
     * Register a new user with given username and password.
     * @throws IllegalArgumentException if username or password is invalid
     * @throws RuntimeException if username already exists
     */
    void signup(String username, String password);

    /**
     * Return all users as DTOs (id + username)
     */
    List<UserDto> getAllUsers();
}
