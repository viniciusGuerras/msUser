package com.compassuol.sp.challenge.msuser.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPasswordResponseDto {

    private String email;
    private String password;
}
