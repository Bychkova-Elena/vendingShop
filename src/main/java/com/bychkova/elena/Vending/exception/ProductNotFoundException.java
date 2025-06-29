package com.bychkova.elena.Vending.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ProductNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(Long productId) {
        super(String.format("Product with id %s not found", productId), "RESOURCE_NOT_FOUND");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}