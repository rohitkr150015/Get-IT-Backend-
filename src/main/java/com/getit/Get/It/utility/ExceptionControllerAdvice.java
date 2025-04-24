package com.getit.Get.It.utility;


import com.getit.Get.It.exception.GetItException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice

public class ExceptionControllerAdvice {

    @Autowired
    private Environment environment;

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<ErrorInfo>GeneralException(Exception exception) {

        ErrorInfo errorInfo=new ErrorInfo(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());

        return new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value= GetItException.class)
    public ResponseEntity<ErrorInfo>GeneralException(GetItException exception) {

        String message=environment.getProperty(exception.getMessage());
        ErrorInfo errorInfo=new ErrorInfo(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());

        return new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler({MethodArgumentNotValidException.class , ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo>validatorExceptionHandler(Exception exception){
        String message="";
        if(exception instanceof MethodArgumentNotValidException maveException){
            message=maveException.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" " ));
        }
        else {
            ConstraintViolationException constraintViolationException=(ConstraintViolationException)exception;
            message=constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());

        }
        return new ResponseEntity<>(new ErrorInfo(message, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);

    }

    }


