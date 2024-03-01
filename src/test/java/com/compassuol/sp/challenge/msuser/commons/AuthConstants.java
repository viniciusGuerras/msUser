package com.compassuol.sp.challenge.msuser.commons;

import com.compassuol.sp.challenge.msuser.domain.jwt.dto.AuthenticationResponseDto;
import com.compassuol.sp.challenge.msuser.domain.jwt.service.JwtService;
import com.compassuol.sp.challenge.msuser.web.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthConstants {

    @Autowired
    private JwtService service;

    public static LoginRequestDto VALID_LOGIN_REQUEST = new LoginRequestDto(
            "vinicius@gmail.com",
            "guerras"
    );


    public static LoginRequestDto INVALID_EMAIL_LOGIN_REQUEST = new LoginRequestDto(
            "vinicius",
            "guerras"
    );

    public static LoginRequestDto INVALID_PASSWORD_LOGIN_REQUEST = new LoginRequestDto(
            "vinicius",
            "123"
    );

}
