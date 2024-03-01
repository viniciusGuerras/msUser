package com.compassuol.sp.challenge.msuser.controllers;

import com.compassuol.sp.challenge.msuser.domain.exceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.exceptions.UniqueFieldValidationException;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.UserDetailsService;
import com.compassuol.sp.challenge.msuser.domain.service.UserService;
import com.compassuol.sp.challenge.msuser.web.controller.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.compassuol.sp.challenge.msuser.commons.UserConstants.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(value = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private JwtService jwtServiceBean;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserService userService;

    @Test
    public void createUser_withValidCredentials_ReturnsUserCreatedWithStatus201() throws Exception{
        when(userService.create(any())).thenReturn(USER);

        mockMvc.perform(
            post("/v1/users")
                    .content(objectMapper.writeValueAsString(USER))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void createUser_withInvalidCredentials_ReturnsErrorWithStatus422() throws Exception{
        mockMvc.perform(
                        post("/v1/users")
                                .content(objectMapper.writeValueAsString(USER_NULL))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createUser_duplicated_ReturnsErrorWithStatus422() throws Exception{
        when(userService.create(any())).thenThrow(new UniqueFieldValidationException("Field already on database"));

        mockMvc.perform(
                        post("/v1/users")
                                .content(objectMapper.writeValueAsString(USER))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void getById_withValidId_ReturnsStatus200() throws Exception{
        Long id = 1L;
        when(userService.findById(anyLong())).thenReturn(USER);

         mockMvc.perform(
                        get("/v1/users/" + id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getById_withInvalidId_ReturnsErrorWithStatus404() throws Exception{
        Long id = 1L;
        when(userService.findById(anyLong())).thenThrow(new EntityNotFoundException("Id not found"));

        mockMvc.perform(
                        get("/v1/users/" + id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUser_withValidCredentials_ReturnsStatus200() throws Exception{
        Long id = 1L;
        doNothing().when(userService).updateInfo(anyLong(), any());

        mockMvc.perform(
                        put("/v1/users/1")
                                .content(objectMapper.writeValueAsString(USER))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser_withInvalidCredentials_ReturnsStatus422() throws Exception{
        mockMvc.perform(
                        put("/v1/users/1")
                                .content(objectMapper.writeValueAsString(USER_NULL))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void updateUserPassword_withValidCredentials_ReturnsStatus200() throws Exception{
        Long id = 1L;
        doNothing().when(userService).updatePassword(anyLong(), any());

        mockMvc.perform(
                        put("/v1/users/1/password")
                                .content(objectMapper.writeValueAsString(PASSWORD))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserPassword_withInvalidCredentials_ReturnsStatus200() throws Exception{
        mockMvc.perform(
                        put("/v1/users/1/password")
                                .content(objectMapper.writeValueAsString(INVALID_PASSWORD))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }





}
