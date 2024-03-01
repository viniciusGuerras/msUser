package com.compassuol.sp.challenge.msuser.services;


import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.model.UserAddress;
import com.compassuol.sp.challenge.msuser.domain.openFeing.MsAddressConsumer;
import com.compassuol.sp.challenge.msuser.domain.rabbitMq.RabbitProducer;
import com.compassuol.sp.challenge.msuser.domain.repository.UserAddressRepository;
import com.compassuol.sp.challenge.msuser.domain.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.web.dto.AddressResponseDto;
import com.compassuol.sp.challenge.msuser.web.dto.CepDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserAddressService {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAddressRepository addressRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private com.compassuol.sp.challenge.msuser.domain.service.UserAddressService service;

    @Mock
    private MsAddressConsumer msAddressConsumer;

    @Mock
    private RabbitProducer rabbitProducerMock;


    @Test
    public void saveNewAddress(){
        User oldUser = new User();
        oldUser.setId(1L);

        User newUser = new User();
        newUser.setEmail("test@example.com");
        newUser.setCep("12345-678");

        CepDto cepDto = new CepDto();
        cepDto.setCep("12345678");

        UserAddress userAddress = new UserAddress();
        userAddress.setId(1L);
        userAddress.setUser(oldUser);

        when(jwtService.GenerateToken(newUser.getEmail())).thenReturn("token");
        when(msAddressConsumer.saveAddress(any(), any())).thenReturn(new AddressResponseDto(1L));
        when(addressRepositoryMock.findUserAddressByUserId(oldUser.getId())).thenReturn(userAddress);

        assertDoesNotThrow(() -> service.saveNewAddress(oldUser, newUser));

    }
}
