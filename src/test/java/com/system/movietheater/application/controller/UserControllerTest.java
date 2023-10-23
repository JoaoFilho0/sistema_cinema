package com.system.movietheater.application.controller;

import com.system.movietheater.application.dto.user.RegisterUserDto;
import com.system.movietheater.domain.model.User;
import com.system.movietheater.infrastructure.exceptions.EmailAlreadyRegisteredException;
import com.system.movietheater.infrastructure.persistence.repository.UserRepository;
import com.system.movietheater.usercase.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private RegisterUserDto registerUserDto;

    @Test
    void shouldReturnError400ForRegistrationRequestWithErrors() throws Exception {
        String json = "{}";

        var response = mvc.perform(
                post("/usuario")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void shouldReturnCode201ForRegistrationRequestWithNotErrors() throws Exception {
        User user = User.builder().id(1L).email("john@gmail.com").name("John").build();

        when(userService.register(any(RegisterUserDto.class))).thenReturn(user);
        String json = """
                {
                  "name": "John",
                  "email": "john@gmail.com",
                  "password": "123456"
                }
                """;

        var response = mvc.perform(
                post("/usuario")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());
    }
}