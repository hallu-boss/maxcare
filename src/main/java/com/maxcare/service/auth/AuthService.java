package com.maxcare.service.auth;

import com.maxcare.domain.User;
import com.maxcare.service.auth.dto.RegisterRequest;
import com.maxcare.service.auth.dto.RegisterResponse;
import org.springframework.stereotype.Service;

import java.util.Locale;

public interface AuthService {
    RegisterResponse register(RegisterRequest request, Locale locale);
}
