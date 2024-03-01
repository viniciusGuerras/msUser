package com.compassuol.sp.challenge.msuser.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @NotBlank
    @Size(max = 160)
    private String password;
}
