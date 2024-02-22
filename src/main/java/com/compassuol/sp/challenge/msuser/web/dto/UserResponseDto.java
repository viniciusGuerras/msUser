package com.compassuol.sp.challenge.msuser.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthdate;
    private String email;
    private String cep;
    private Boolean active;
}
