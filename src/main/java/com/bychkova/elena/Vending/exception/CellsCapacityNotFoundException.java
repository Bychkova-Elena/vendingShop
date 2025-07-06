package com.bychkova.elena.Vending.exception;

import com.bychkova.elena.Vending.entity.Cell;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class CellsCapacityNotFoundException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CellsCapacityNotFoundException(Cell cell) {
        super(String.format("CellsCapacity with cell_id %s not found", cell.getId()), "RESOURCE_NOT_FOUND");
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
