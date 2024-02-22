package com.compassuol.sp.challenge.msuser.web.dto.mapper;

import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.web.dto.UserCreateDto;
import com.compassuol.sp.challenge.msuser.web.dto.UserPasswordDto;
import com.compassuol.sp.challenge.msuser.web.dto.UserResponseDto;
import org.modelmapper.ModelMapper;

public class UserMapper {

        public static UserResponseDto toDto(User user){
            return new ModelMapper().map(user, UserResponseDto.class);
        }

        public static <T> User toUser(T dto){
            return new ModelMapper().map(dto, User.class);
        }
}
