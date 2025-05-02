package com.fitness.challenge.api.service.impl;

import com.fitness.challenge.api.exception.AuthException;
import com.fitness.challenge.api.model.User;
import com.fitness.challenge.api.repository.UserRepository;
import com.fitness.challenge.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User login(String username, String password) throws AuthException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new AuthException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthException("Invalid password");
        }

        return user;
    }

    @Override
    @Transactional
    public User register(String username, String email, String password, String firstName, String lastName) throws AuthException {
        if (userRepository.existsByUsername(username)) {
            throw new AuthException("Username already exists");
        }

        if (userRepository.existsByEmail(email)) {
            throw new AuthException("Email already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);

        return userRepository.save(user);
    }

    @Override
    public void logout() {
        // In a stateless application, logout is handled client-side
    }

    @Override
    public User getCurrentUser() throws AuthException {
        // This should be implemented with Spring Security's current user context
        // For now, we'll throw an exception as this needs proper security implementation
        throw new AuthException("Not implemented");
    }
} 