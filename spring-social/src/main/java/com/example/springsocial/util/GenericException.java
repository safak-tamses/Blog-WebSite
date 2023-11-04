package com.example.springsocial.util;


import com.example.springsocial.exception.*;
import com.example.springsocial.model.GenericExceptionResponse;
import com.example.springsocial.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GenericException extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            UserAlreadyExistsException.class ,
            BadRequestException.class,
            DataNotFoundException.class,
            OAuth2AuthenticationProcessingException.class,
            PostNotFoundException.class,
            ResourceNotFoundException.class,
            SaveFailedException.class,
            UserAlreadyExistsException.class,
            UserNotFound.class
    })
    public ResponseEntity<Object> handleCustomException(Exception e) {
        GenericExceptionResponse error = new GenericExceptionResponse(e.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }




}
