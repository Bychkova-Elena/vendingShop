package com.bychkova.elena.Vending.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class NotBeNullException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NotBeNullException(String item) {
        super(item + " not be null", "BAD_REQUEST");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
