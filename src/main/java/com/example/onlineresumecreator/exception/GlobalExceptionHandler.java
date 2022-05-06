package com.example.onlineresumecreator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.CONFLICT,reason = "Conflict Data Found")
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public void DataConflictException(){

    }
}
