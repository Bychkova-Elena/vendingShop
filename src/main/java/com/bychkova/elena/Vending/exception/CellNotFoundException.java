package com.bychkova.elena.Vending.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class CellNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CellNotFoundException(Long cellId) {
        super(String.format("Cell with id %s not found", cellId), "RESOURCE_NOT_FOUND");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}