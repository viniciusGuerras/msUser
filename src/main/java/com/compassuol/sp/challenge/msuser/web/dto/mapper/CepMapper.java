package com.compassuol.sp.challenge.msuser.web.dto.mapper;

import com.compassuol.sp.challenge.msuser.web.dto.CepDto;

public class CepMapper {

    public static CepDto toDto(String cep){
        return new CepDto(cep);
    }
}
