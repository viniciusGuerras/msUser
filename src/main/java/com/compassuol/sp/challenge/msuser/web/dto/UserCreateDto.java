package com.compassuol.sp.challenge.msuser.web.dto;


import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserCreateDto {
    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Cep must match xxxxx-xxx format")
    private String cep;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotNull
    private Boolean active;
}