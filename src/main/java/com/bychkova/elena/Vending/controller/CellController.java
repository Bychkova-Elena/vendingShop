package com.bychkova.elena.Vending.controller;
import com.bychkova.elena.Vending.dto.CellResponse;
import com.bychkova.elena.Vending.dto.addProductInCellRq;
import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.service.CellService;
import jakarta.validation.Valid;
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

        return convertListToResponse(cellService.getAllCells());
    }

    @PutMapping("/{id}/add/product")
    public CellResponse putProductInCell(@PathVariable Long id,
                                         @Valid @RequestBody addProductInCellRq productInCellRq) {
        Cell cell = cellService.putProductInCell(id, productInCellRq.getProductId(), productInCellRq.getQuantity());
        return convertToResponse(cell);
    }

    @PutMapping("{id}/decrease/product")
    public CellResponse decreaseProductInCell(@PathVariable Long id) {
        Cell cell = cellService.decreaseProductInCell(id);

        return convertToResponse(cell);
    }

    private CellResponse convertToResponse(Cell c) {
        CellResponse cellResponse = new CellResponse();
        cellResponse.setId(c.getId());
        cellResponse.setVendingId(c.getVending().getId());
        if(c.getProduct() != null) {
            cellResponse.setProductId(c.getProduct().getId());
        }
        cellResponse.setFreePlacesCount(c.getFreePlacesCount());

        return cellResponse;
    }

    private Iterable<CellResponse> convertListToResponse(Iterable<Cell> cells) {
        ArrayList<CellResponse> cellList = new ArrayList<>();

        for (Cell c : cells) {
            CellResponse cellResponse = convertToResponse(c);
            cellList.add(cellResponse);
        }

        return cellList;
    }
}
