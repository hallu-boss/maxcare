package com.maxcare.service.auth;

import com.maxcare.common.ApiException;
import com.maxcare.common.ErrorCode;
import com.maxcare.domain.Language;
import com.maxcare.domain.User;
import com.maxcare.domain.UserRole;
import com.maxcare.domain.UserStatus;
import com.maxcare.repository.UserRepository;
import com.maxcare.service.auth.dto.RegisterRequest;
import com.maxcare.service.auth.dto.RegisterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Locale;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request, Locale locale) {
        String email = request.email();
        System.out.println(email);

        if(userRepository.existsByEmail(email)) {
            throw emailAlreadyExists();
        }

        User user = User.newPatient(email, "abc", "def", "ghi", LocalDate.of(2000, 1, 15), Language.PL);

        try {
            userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException e) {
            log.error("Failed to save user", e);
            System.out.println("Failed to save user");
            throw e;
        }

        return RegisterResponse.from(user);
    }

    private ApiException emailAlreadyExists() {
        return new ApiException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
