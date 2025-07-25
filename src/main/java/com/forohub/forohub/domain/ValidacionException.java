package com.forohub.forohub.domain;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidacionException extends RuntimeException {
    private final HttpStatus status;
    public ValidacionException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
