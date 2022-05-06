package com.example.onlineresumecreator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "no record with this id")
public class NoRecordWithThisIdException extends RuntimeException {
    public NoRecordWithThisIdException(String message) {
        super(message);
    }
}
