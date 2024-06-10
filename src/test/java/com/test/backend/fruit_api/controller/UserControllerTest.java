package com.test.backend.fruit_api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.backend.fruit_api.entity.User;
import com.test.backend.fruit_api.model.RegisterUserRequest;
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
class UserControllerTest {

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
    void testRegisterUserSuccess() throws Exception{
        RegisterUserRequest request =  new RegisterUserRequest();

        request.setUsername("jerryandriantop");
        request.setPassword("password123");
        request.setName("Jerry Andrianto Pangaribuan");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertEquals("OK", response.getData());
        });
    }

    @Test
    void testRegisterUserBadRequest() throws Exception{
        RegisterUserRequest request =  new RegisterUserRequest();

        request.setUsername("");
        request.setPassword("");
        request.setName("");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testRegisterUserDuplicate() throws Exception{
        User user = new User();
        user.setUsername("jerryandriantop");
        user.setPassword(BCrypt.hashpw("password123", BCrypt.gensalt()));
        user.setName("Jerry Andrianto Pangaribuan");
        userRepository.save(user);

        RegisterUserRequest request =  new RegisterUserRequest();
        request.setUsername("jerryandriantop");
        request.setPassword("password123");
        request.setName("Jerry Andrianto Pangaribuan");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNotNull(response.getErrors());
        });
    }

}