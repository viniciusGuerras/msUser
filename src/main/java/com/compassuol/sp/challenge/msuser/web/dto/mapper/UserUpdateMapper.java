package com.compassuol.sp.challenge.msuser.web.dto.mapper;

import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.web.dto.UserUpdateDto;
import com.compassuol.sp.challenge.msuser.web.dto.UserUpdateResponseDto;
import org.modelmapper.ModelMapper;

public class UserUpdateMapper {
    public static UserUpdateResponseDto toDto(User user){
        return new ModelMapper().map(user, UserUpdateResponseDto.class);
    }

    public static User toUser(UserUpdateDto dto){
        return new ModelMapper().map(dto, User.class);
    }

}
