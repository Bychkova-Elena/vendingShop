package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.dto.CellResponse;
import com.bychkova.elena.Vending.dto.CellsCapacityResponse;
import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.entity.CellsCapacity;
import com.bychkova.elena.Vending.entity.Product;
import com.bychkova.elena.Vending.exception.*;
import com.bychkova.elena.Vending.mapper.CellMapper;
import com.bychkova.elena.Vending.mapper.CellsCapacityMapper;
import com.bychkova.elena.Vending.repository.CellRepository;
import com.bychkova.elena.Vending.repository.CellsCapacityRepository;
import com.bychkova.elena.Vending.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CellService {

    private final CellRepository cellRepository;
    private final ProductRepository productRepository;
    private final CellsCapacityRepository cellsCapacityRepository;
    private final CellsCapacityMapper cellsCapacityMapper;
    private final CellMapper cellMapper;

    public CellService(CellRepository cellRepository, ProductRepository productRepository, CellsCapacityRepository cellsCapacityRepository, CellMapper cellMapper, CellsCapacityMapper cellsCapacityMapper) {
        this.cellRepository = cellRepository;
        this.productRepository = productRepository;
        this.cellsCapacityRepository = cellsCapacityRepository;
        this.cellMapper = cellMapper;
        this.cellsCapacityMapper = cellsCapacityMapper;
    }

    public Iterable<CellResponse> getAllCells() {
        return cellMapper.convertListToResponse(cellRepository.findAll());
    }

    public CellResponse putProductInCell(Long id, Long productId, int quantity) {
        Cell cell = cellRepository.findById(id).orElseThrow(() -> new CellNotFoundException(id));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        CellsCapacity cellsCapacity = Optional.ofNullable(getCapacityByCell(cell)).orElseThrow(() -> new CellsCapacityNotFoundException(cell));

        boolean isFits = cellsCapacity.getFreePlacesCount() - quantity >= 0;

        if (cellsCapacity.isFull() || !isFits) {
            throw new CellNotEnoughSpaceException(id, cellsCapacity.getFreePlacesCount());
        }

        if (quantity <= 0) {
            throw new NotBeNullException("quantity");
        }

        cell.setProduct(product);
        cellsCapacity.setFreePlacesCount(cellsCapacity.getFreePlacesCount() - quantity);

        return cellMapper.convertToResponse(cellRepository.save(cell));
    }

    public CellsCapacityResponse decreaseProductInCell(Long id) {
        Cell cell = cellRepository.findById(id).orElseThrow(() -> new CellNotFoundException(id));
        CellsCapacity cellsCapacity = Optional.ofNullable(getCapacityByCell(cell)).orElseThrow(() -> new CellsCapacityNotFoundException(cell));

        if (cellsCapacity.isEmpty()) {
            throw new CellIsEmptyException(id);
        }

        var freePlacesCount = cellsCapacity.getFreePlacesCount();
        cellsCapacity.setFreePlacesCount(freePlacesCount + 1);
        if (freePlacesCount == cellsCapacity.getCapacity() - 1) {
            cell.setProduct(null);
            cellRepository.save(cell);
        }

        return cellsCapacityMapper.convertToResponse(cellsCapacityRepository.save(cellsCapacity));
    }

    private CellsCapacity getCapacityByCell(Cell cell) {
        Iterable<CellsCapacity> cellsCapacity = cellsCapacityRepository.findAll();

        for (CellsCapacity element : cellsCapacity) {
            if (element.getCell().equals(cell)) {
                return element;
            }
        }
        return null;
    }
}
