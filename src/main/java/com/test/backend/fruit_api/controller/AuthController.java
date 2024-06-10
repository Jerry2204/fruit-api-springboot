package com.test.backend.fruit_api.controller;

import com.test.backend.fruit_api.model.LoginUserRequest;
import com.test.backend.fruit_api.model.TokenResponse;
import com.test.backend.fruit_api.model.WebResponse;
import com.test.backend.fruit_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request) {
        TokenResponse tokenResponse = authService.Login(request);

        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }

}
