package com.bychkova.elena.Vending.mapper;

import com.bychkova.elena.Vending.dto.CellResponse;
import com.bychkova.elena.Vending.entity.Cell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CellMapper {
    public CellResponse convertToResponse(Cell c) {
        CellResponse cellResponse = new CellResponse();
        cellResponse.setId(c.getId());
        cellResponse.setVendingId(c.getVending().getId());
        if(c.getProduct() != null) {
            cellResponse.setProductId(c.getProduct().getId());
        }

        return cellResponse;
    }

    public Iterable<CellResponse> convertListToResponse(Iterable<Cell> cells) {
        ArrayList<CellResponse> cellList = new ArrayList<>();

        for (Cell c : cells) {
            CellResponse cellResponse = convertToResponse(c);
            cellList.add(cellResponse);
        }

        return cellList;
    }
}
