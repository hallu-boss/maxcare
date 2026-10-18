package com.maxcare.service.auth.dto;

import com.maxcare.domain.User;
import com.maxcare.domain.UserStatus;

public record RegisterResponse(Long id, String email, UserStatus status) {
    public static RegisterResponse from(User user) {
        return new RegisterResponse(user.getId(), user.getEmail(), user.getStatus());
    }
}
