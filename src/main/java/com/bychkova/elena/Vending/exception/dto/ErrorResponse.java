package com.bychkova.elena.Vending.exception.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String errorCode;
    private String path;

    public ErrorResponse(LocalDateTime timestamp, int status,
                         String message, String errorCode) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}