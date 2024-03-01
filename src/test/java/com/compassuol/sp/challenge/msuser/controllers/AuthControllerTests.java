package com.compassuol.sp.challenge.msuser.controllers;

import com.compassuol.sp.challenge.msuser.domain.exceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.UserDetailsService;
import com.compassuol.sp.challenge.msuser.domain.service.AuthService;
import com.compassuol.sp.challenge.msuser.web.controller.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import static com.compassuol.sp.challenge.msuser.commons.AuthConstants.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AuthController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class AuthControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private WebApplicationContext webApplicationContext;

        @MockBean
        private JwtService jwtService;

        @MockBean
        private UserDetailsService userDetailsService;

        @MockBean
        private AuthService authService;

        @Test
        public void loginUser_withValidCredentials_returnsStatus200() throws Exception {
            when(authService.authenticate(any())).thenReturn(anyString());

            mockMvc.perform(
                    post("/v1/login")
                            .content(objectMapper.writeValueAsString(VALID_LOGIN_REQUEST))
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());
        }

        @Test
        public void loginUser_withInvalidCredentials_returnsStatus404() throws Exception {
            when(authService.authenticate(any())).thenThrow(new EntityNotFoundException("Id not found"));

            mockMvc.perform(
                    post("/v1/login")
                            .content(objectMapper.writeValueAsString(VALID_LOGIN_REQUEST))
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNotFound());
        }

        @Test
        public void loginUser_withInvalidEmail_returnsStatus422() throws Exception {
            mockMvc.perform(
                    post("/v1/login")
                            .content(objectMapper.writeValueAsString(INVALID_EMAIL_LOGIN_REQUEST))
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isUnprocessableEntity());
        }

        @Test
        public void loginUser_withInvalidPassword_returnsStatus422() throws Exception {
            mockMvc.perform(
                    post("/v1/login")
                            .content(objectMapper.writeValueAsString(INVALID_PASSWORD_LOGIN_REQUEST))
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isUnprocessableEntity());
        }
}
