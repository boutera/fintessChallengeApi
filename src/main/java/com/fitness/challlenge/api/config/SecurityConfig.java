package com.fitness.challlenge.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                // Admin-only endpoints
                .requestMatchers("/api/challenges/create").hasRole("ADMIN")
                .requestMatchers("/api/achievements/create").hasRole("ADMIN")
                .requestMatchers("/api/workouts/create").hasRole("ADMIN")
                
                // Public endpoints
                .requestMatchers("/api/challenges/**").permitAll()
                .requestMatchers("/api/achievements/**").permitAll()
                .requestMatchers("/api/workouts/**").permitAll()
                .requestMatchers("/api/users/**").permitAll()
                
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
} 