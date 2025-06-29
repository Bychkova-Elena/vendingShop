package com.bychkova.elena.Vending.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class CellNotEnoughSpaceException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CellNotEnoughSpaceException(Long cellId, int freePlacesCount) {
        super(String.format("There is not enough free space in the cell. There are %d free spaces in cell %d.",
                freePlacesCount,
                cellId), "BAD_REQUEST");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
