package com.fitness.challenge.api.service;

import com.fitness.challenge.api.exception.AuthException;
import com.fitness.challenge.api.model.User;

public interface AuthService {
    User login(String username, String password) throws AuthException;
    User register(String username, String email, String password, String firstName, String lastName) throws AuthException;
    void logout();
    User getCurrentUser() throws AuthException;
}
 