package com.compassuol.sp.challenge.msuser.web.dto.mapper;

import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.web.dto.UserPasswordDto;
import com.compassuol.sp.challenge.msuser.web.dto.UserPasswordResponseDto;
import org.modelmapper.ModelMapper;

public class UserPasswordMapper{

    public static UserPasswordResponseDto toDto(User user){
        return new ModelMapper().map(user, UserPasswordResponseDto.class);
    }

    public static User toUser(UserPasswordDto dto){
        return new ModelMapper().map(dto, User.class);
    }
}
