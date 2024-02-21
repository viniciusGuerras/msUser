package com.compassuol.sp.challenge.msuser.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserUpdateResponseDto {
        private String firstName;
        private String lastName;
        private String cpf;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date birthdate;
        private String email;
        private String cep;
        private Boolean active;
}
