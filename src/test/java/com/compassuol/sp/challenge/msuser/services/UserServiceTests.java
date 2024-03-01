package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.domain.exceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.UserDetailsService;
import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.openFeing.MsAddressConsumer;
import com.compassuol.sp.challenge.msuser.domain.rabbitMq.RabbitProducer;
import com.compassuol.sp.challenge.msuser.domain.repository.UserAddressRepository;
import com.compassuol.sp.challenge.msuser.domain.repository.UserRepository;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.compassuol.sp.challenge.msuser.commons.UserConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAddressRepository addressRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserService userService;

    @Mock
    private MsAddressConsumer msAddressConsumer;

    @Mock
    private RabbitProducer rabbitProducerMock;

    @Test
    public void createUser_withValidCredentials(){
        when(userRepository.save(any())).thenReturn(USER);
        when(msAddressConsumer.saveAddress(any(), any())).thenReturn(new AddressResponseDto(1L));

        when(jwtService.GenerateToken(any())).thenReturn(null);
        when(passwordEncoder.encode(any())).thenReturn(null);

        User test_user = userService.create(USER);

        assertDoesNotThrow(() -> userService.create(USER));
        assertSame(test_user, USER);
        assertSame(test_user.getEmail(), USER.getEmail());
    }

    @Test
    public void getById_withValidId_ReturnsUser(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(USER));

        Long id = 1L;
        User test_user = userService.findById(id);

        assertSame(test_user, USER);
    }

    @Test
    public void getById_withInvalidId_AssertThrows(){
        when(userRepository.findById(anyLong())).thenThrow(new EntityNotFoundException("Id not found"));

        Long id = 1L;
        assertThrows(EntityNotFoundException.class, () -> userService.findById(id));
    }

    @Test
    public void updatePassword_withValidCredentials(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(USER));

        Long id = 1L;
        assertDoesNotThrow(() -> userService.updatePassword(id, USER));
    }

    @Test
    public void updateInformation_withValidCredentials(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(USER));

        Long id = 1L;
        assertDoesNotThrow(() -> userService.updateInfo(id, USER));
    }

}
