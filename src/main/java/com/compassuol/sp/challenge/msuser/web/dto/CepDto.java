package com.compassuol.sp.challenge.msuser.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CepDto {
    @NotBlank
    @Pattern(regexp = "^\\d{8}$")
    private String cep;
}
