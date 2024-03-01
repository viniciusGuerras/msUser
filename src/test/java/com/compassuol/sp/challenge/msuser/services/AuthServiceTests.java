package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.domain.exceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.UserDetailsService;
import com.compassuol.sp.challenge.msuser.domain.openFeing.MsAddressConsumer;
import com.compassuol.sp.challenge.msuser.domain.rabbitMq.RabbitProducer;
import com.compassuol.sp.challenge.msuser.domain.repository.UserAddressRepository;
import com.compassuol.sp.challenge.msuser.domain.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.domain.service.AuthService;
import com.compassuol.sp.challenge.msuser.domain.service.UserService;
import com.compassuol.sp.challenge.msuser.web.dto.AddressResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.compassuol.sp.challenge.msuser.commons.UserConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    @InjectMocks
    private AuthService service;

    @Mock
    private UserRepository repository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private MsAddressConsumer msAddressConsumer;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RabbitProducer rabbitProducerMock;

    @Test
    public void loginUser_withValidInformation(){
        doNothing().when(rabbitProducerMock).sendMessage(any());
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtService.GenerateToken(any())).thenReturn(null);
        when(repository.findByEmail(any())).thenReturn(Optional.of(USER));

        assertDoesNotThrow(() -> service.authenticate(USER));
    }

    @Test
    public void loginUser_withInvalidInformation(){
        when(repository.findByEmail(any())).thenThrow(new EntityNotFoundException("Email not found"));
        assertThrows(EntityNotFoundException.class, () -> service.authenticate(USER_NULL));
    }


}
