package com.compassuol.sp.challenge.msuser.domain.service;

import com.compassuol.sp.challenge.msuser.domain.exceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.domain.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.domain.jwt.dto.AuthenticationResponseDto;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.domain.rabbitMq.RabbitProducer;
import com.compassuol.sp.challenge.msuser.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.compassuol.sp.challenge.msuser.domain.enums.EventTypeEnumeration.LOGIN;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final RabbitProducer rabbitProducer;

    //Code for a register system
 /*
    public AuthenticationResponseDto register(User request){
        User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setCpf(request.getCpf());
            user.setBirthdate(request.getBirthdate());
            user.setEmail(request.getEmail());
            user.setCep(request.getCep());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setActive(request.getActive());

        user = repository.save(user);

        String token = jwtService.GenerateToken(user.getEmail());
        return new AuthenticationResponseDto(token);
    }*/

    @Transactional
    public AuthenticationResponseDto authenticate(User request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        User user = repository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User not Found")
        );

        String token = jwtService.GenerateToken(user.getEmail());

        Notification log = new Notification(request.getEmail(), LOGIN);
        rabbitProducer.sendMessage(log);

        return new AuthenticationResponseDto(token);
    }
}
