package com.compassuol.sp.challenge.msuser.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDto {

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
    private Date birthdate;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String cep;

    @NotBlank
    private String password;

    @NotNull
    private Boolean active;
}
