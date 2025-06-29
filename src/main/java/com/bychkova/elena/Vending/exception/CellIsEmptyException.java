package com.bychkova.elena.Vending.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class CellIsEmptyException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CellIsEmptyException(Long cellId) {
        super(String.format("Cell with id %s is empty", cellId), "BAD_REQUEST");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}