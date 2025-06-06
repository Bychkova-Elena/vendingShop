package com.bychkova.elena.Vending.controller;
import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.service.CellService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/cells")
public class CellController {

    @Autowired
    private CellService cellService;

    @GetMapping
    public Iterable<Cell> getAllCells() {
        return cellService.getAllCells();
    }
}
