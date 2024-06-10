package com.test.backend.fruit_api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.backend.fruit_api.entity.User;
import com.test.backend.fruit_api.model.LoginUserRequest;
import com.test.backend.fruit_api.model.TokenResponse;
import com.test.backend.fruit_api.model.WebResponse;
import com.test.backend.fruit_api.repository.UserRepository;
import com.test.backend.fruit_api.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void loginFailedUserNotFound() throws Exception {

        LoginUserRequest userRequest = new LoginUserRequest();
        userRequest.setUsername("jerryandriantopunkrib");
        userRequest.setPassword("password123");

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userRequest))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {

            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void loginFailedWrongPassword() throws Exception {

        User user = new User();
        user.setUsername("jerryandriantopunkrib");
        user.setPassword(BCrypt.hashpw("password123", BCrypt.gensalt()));
        user.setName("Jerry Andrianto Pangaribuan");
        userRepository.save(user);

        LoginUserRequest userRequest = new LoginUserRequest();
        userRequest.setUsername("jerryandriantopunkrib");
        userRequest.setPassword("salah123");

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userRequest))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {

            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void loginSuccess() throws Exception {

        User user = new User();
        user.setUsername("jerryandriantopunkrib");
        user.setPassword(BCrypt.hashpw("password123", BCrypt.gensalt()));
        user.setName("Jerry Andrianto Pangaribuan");
        userRepository.save(user);

        LoginUserRequest userRequest = new LoginUserRequest();
        userRequest.setUsername("jerryandriantopunkrib");
        userRequest.setPassword("password123");

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userRequest))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {

            });
            assertNull(response.getErrors());
            assertNotNull(response.getData().getToken());
            assertNotNull(response.getData().getExpiredAt());

            User userDb = userRepository.findById("jerryandriantopunkrib").orElse(null);
            assertNotNull(userDb);
            assertEquals(userDb.getToken(), response.getData().getToken());
            assertEquals(userDb.getTokenExpiredAt(), response.getData().getExpiredAt());
        });
    }

}