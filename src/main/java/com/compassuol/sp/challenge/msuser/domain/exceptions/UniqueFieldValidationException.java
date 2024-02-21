package com.compassuol.sp.challenge.msuser.domain.exceptions;

public class UniqueFieldValidationException extends RuntimeException{
    public UniqueFieldValidationException(String message) {
        super(message);
    }
}
