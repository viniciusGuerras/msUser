package com.compassuol.sp.challenge.msuser.web.dto.mapper;

import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.web.dto.LoginRequestDto;
import org.modelmapper.ModelMapper;

public class UserLoginMapper {

    public static LoginRequestDto toDto(User user){
        return new ModelMapper().map(user, LoginRequestDto.class);
    }

    public static User toUser(LoginRequestDto dto){
            return new ModelMapper().map(dto, User.class);
        }

}

