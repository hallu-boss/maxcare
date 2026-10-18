package com.maxcare.service.auth.dto;

import java.time.LocalDate;

public record RegisterRequest(
        String email,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String password,
        String captchaToken,
        String language
) {
    @Override
    public String toString() {
        return "RegisterRequest{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", captchaToken='" + captchaToken + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
