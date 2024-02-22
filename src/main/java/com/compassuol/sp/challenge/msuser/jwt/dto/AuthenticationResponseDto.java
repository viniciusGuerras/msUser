package com.compassuol.sp.challenge.msuser.jwt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {
    private String token;

}
