package com.bychkova.elena.Vending.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class VendingNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public VendingNotFoundException(Long vendingId) {
        super(String.format("Vending with id %s not found", vendingId), "RESOURCE_NOT_FOUND");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}