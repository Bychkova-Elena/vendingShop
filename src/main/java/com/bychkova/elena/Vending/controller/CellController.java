package com.bychkova.elena.Vending.controller;
import com.bychkova.elena.Vending.dto.CellResponse;
import com.bychkova.elena.Vending.dto.addProductInCellRq;
import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.mapper.CellMapper;
import com.bychkova.elena.Vending.service.CellService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cells")
public class CellController {

    private final CellService cellService;
    private final CellMapper mapper;

    public CellController(CellService cellService, CellMapper mapper) {
        this.cellService = cellService;
        this.mapper = mapper;
    }

    @GetMapping
    public Iterable<CellResponse> getAllCells() {

        return mapper.convertListToResponse(cellService.getAllCells());
    }

    @PutMapping("/{id}/add/product")
    public CellResponse putProductInCell(@PathVariable Long id,
                                         @Valid @RequestBody addProductInCellRq productInCellRq) {
        Cell cell = cellService.putProductInCell(id, productInCellRq.getProductId(), productInCellRq.getQuantity());
        return mapper.convertToResponse(cell);
    }

    @PutMapping("{id}/decrease/product")
    public CellResponse decreaseProductInCell(@PathVariable Long id) {
        Cell cell = cellService.decreaseProductInCell(id);

        return mapper.convertToResponse(cell);
    }
}
