package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CellService {

    @Autowired
    private CellRepository cellRepository;

    public Iterable<Cell> getAllCells() {
        return cellRepository.findAll();
    }
}
