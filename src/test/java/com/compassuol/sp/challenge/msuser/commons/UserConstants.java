package com.compassuol.sp.challenge.msuser.commons;

import com.compassuol.sp.challenge.msuser.domain.model.User;
import com.compassuol.sp.challenge.msuser.web.dto.UserPasswordDto;

import java.util.Date;

public class UserConstants {

    public static final User USER = new User(
            null,
            "Gustavo"
            , "Silva"
            , "725.598.470-35"
            , new Date()
            , "gustavo@email.com"
            , "01310-930"
            , "12345678"
            , true);

    public static final User USER_UPDATED_PASSWORD = new User(
            null,
            ""
            , ""
            , ""
            , new Date()
            , ""
            , ""
            , "987654321"
            , true);

    public static final User USER_NULL = new User(
            null,
            ""
            , ""
            , ""
            , new Date()
            , ""
            , ""
            , ""
            , true);

    public static final User USER_WRONG_CEP = new User(
            null,
            "Gustavo"
            , "Silva"
            , "725.598.470-35"
            , new Date()
            , "gustavo@email.com"
            , "99999-999"
            , "12345678"
            , true);

    public static final User USER_WITH_ID = new User(
            1L,
            "Gustavo"
            , "Silva"
            , "725.598.470-35"
            , new Date()
            , "gustavo@email.com"
            , "99999-999"
            , "12345678"
            , true);

    public static final UserPasswordDto PASSWORD = new UserPasswordDto(
            "12345678"
    );

    public static final UserPasswordDto INVALID_PASSWORD = new UserPasswordDto(
            "123"
    );
}
