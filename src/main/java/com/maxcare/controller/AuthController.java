package com.maxcare.controller;

import com.maxcare.service.auth.AuthService;
import com.maxcare.service.auth.dto.RegisterRequest;
import com.maxcare.service.auth.dto.RegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody RegisterRequest request, Locale locale) {
        return authService.register(request, locale);
    }
}
