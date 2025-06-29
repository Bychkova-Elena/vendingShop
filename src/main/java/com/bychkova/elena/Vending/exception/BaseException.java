package com.bychkova.elena.Vending.exception;

import org.springframework.http.HttpStatus;
import com.bychkova.elena.Vending.exception.dto.ErrorResponse;


import java.time.LocalDateTime;

public abstract class BaseException extends RuntimeException {
    private final LocalDateTime timestamp;
    private final String errorCode;

    public BaseException(String message, String errorCode) {
        super(message);
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
    }

    public abstract HttpStatus getHttpStatus();

    public ErrorResponse toErrorResponse() {
        return new ErrorResponse(
                timestamp,
                getHttpStatus().value(),
                getMessage(),
                errorCode
        );
    }
}