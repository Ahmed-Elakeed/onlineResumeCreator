package com.example.onlineresumecreator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Existed Data Entry")
public class SimilarDataAlreadyExistsException extends RuntimeException{
    public SimilarDataAlreadyExistsException(String message){
        super(message);
    }
}
