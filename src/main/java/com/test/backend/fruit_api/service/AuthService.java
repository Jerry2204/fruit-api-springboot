package com.test.backend.fruit_api.service;

import com.test.backend.fruit_api.entity.User;
import com.test.backend.fruit_api.model.LoginUserRequest;
import com.test.backend.fruit_api.model.TokenResponse;
import com.test.backend.fruit_api.repository.UserRepository;
import com.test.backend.fruit_api.security.BCrypt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse Login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            // success
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }

}
