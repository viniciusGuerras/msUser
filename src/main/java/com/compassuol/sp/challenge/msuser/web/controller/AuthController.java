package com.compassuol.sp.challenge.msuser.web.controller;

import com.compassuol.sp.challenge.msuser.domain.service.AuthService;
import com.compassuol.sp.challenge.msuser.jwt.dto.AuthenticationResponseDto;
import com.compassuol.sp.challenge.msuser.web.dto.LoginRequestDto;
import com.compassuol.sp.challenge.msuser.web.dto.mapper.UserLoginMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/login")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDto> userLogin(@Valid @RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.authenticate(UserLoginMapper.toUser(dto)));
    }
}
