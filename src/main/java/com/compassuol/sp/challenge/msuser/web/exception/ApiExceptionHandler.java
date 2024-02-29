package com.compassuol.sp.challenge.msuser.web.exception;

import com.compassuol.sp.challenge.msuser.domain.exceptions.CepNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.exceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.domain.exceptions.UniqueFieldValidationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, RuntimeException.class})
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result){
        log.error("Api Error - ", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY,"Invalid field(s)", result));
    }


    @ExceptionHandler(UniqueFieldValidationException.class)
    public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex,HttpServletRequest request){
        log.error("Api Error - ", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT,ex.getMessage()));
    }

    @ExceptionHandler({EntityNotFoundException.class, CepNotFoundException.class})
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex,HttpServletRequest request){
        log.error("Api Error - ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND,ex.getMessage()));
    }

}
