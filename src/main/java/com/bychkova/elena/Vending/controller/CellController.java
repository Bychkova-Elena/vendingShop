package com.bychkova.elena.Vending.controller;
import com.bychkova.elena.Vending.dto.CellResponse;
import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.service.CellService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/cells")
public class CellController {

    @Autowired
    private CellService cellService;

    @GetMapping
    public Iterable<CellResponse> getAllCells() {

        return convertToResponse(cellService.getAllCells());
    }

    private Iterable<CellResponse> convertToResponse(Iterable<Cell> cells) {
        ArrayList<CellResponse> cellList = new ArrayList<>();

        for (Cell c : cells) {
            CellResponse cellResponse = new CellResponse();
            cellResponse.setId(c.getId());
            cellResponse.setVendingId(c.getVending().getId());
            cellList.add(cellResponse);
        }

        return cellList;
    }
}
