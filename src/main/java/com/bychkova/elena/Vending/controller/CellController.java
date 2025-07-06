package com.bychkova.elena.Vending.controller;
import com.bychkova.elena.Vending.dto.CellResponse;
import com.bychkova.elena.Vending.dto.CellsCapacityResponse;
import com.bychkova.elena.Vending.dto.addProductInCellRq;
import com.bychkova.elena.Vending.service.CellService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cells")
public class CellController {

    private final CellService cellService;

    public CellController(CellService cellService) {
        this.cellService = cellService;
    }

    @GetMapping
    public Iterable<CellResponse> getAllCells() {

        return cellService.getAllCells();
    }

    @PutMapping("/{id}/add/product")
    public CellResponse putProductInCell(@PathVariable Long id,
                                         @Valid @RequestBody addProductInCellRq productInCellRq) {
        return cellService.putProductInCell(id, productInCellRq.getProductId(), productInCellRq.getQuantity());
    }

    @PutMapping("{id}/decrease/product")
    public CellsCapacityResponse decreaseProductInCell(@PathVariable Long id) {
        return cellService.decreaseProductInCell(id);
    }
}
