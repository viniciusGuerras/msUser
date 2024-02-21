package com.compassuol.sp.challenge.msuser.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserPasswordResponseDto {

    private String email;
    private String password;
}
