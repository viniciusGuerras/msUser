package com.compassuol.sp.challenge.msuser.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class UserUpdateResponseDto {
        private String firstName;
        private String lastName;
        private String cpf;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date birthdate;
        private String email;
        private String cep;
        private Boolean active;
}