package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.dto.CellResponse;
import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.entity.Product;
import com.bychkova.elena.Vending.exception.*;
import com.bychkova.elena.Vending.mapper.CellMapper;
import com.bychkova.elena.Vending.repository.CellRepository;
import com.bychkova.elena.Vending.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CellService {

    private final CellRepository cellRepository;
    private final ProductRepository productRepository;
    private final CellMapper mapper;

    public CellService(CellRepository cellRepository, ProductRepository productRepository, CellMapper mapper) {
        this.cellRepository = cellRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public Iterable<CellResponse> getAllCells() {
        return mapper.convertListToResponse(cellRepository.findAll());
    }

    public CellResponse putProductInCell(Long id, Long productId, int quantity) {
        Cell cell = cellRepository.findById(id).orElseThrow(() -> new CellNotFoundException(id));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        boolean isFits = cell.getFreePlacesCount() - quantity >= 0;

        if (cell.isFull() || !isFits) {
            throw new CellNotEnoughSpaceException(id, cell.getFreePlacesCount());
        }

        if (quantity <= 0) {
            throw new NotBeNullException("quantity");
        }

        cell.setProduct(product);
        cell.setFreePlacesCount(cell.getFreePlacesCount() - quantity);

        return mapper.convertToResponse(cellRepository.save(cell));
    }

    public CellResponse decreaseProductInCell(Long id) {
        Cell cell = cellRepository.findById(id).orElseThrow(() -> new CellNotFoundException(id));

        if (cell.isEmpty()) {
            throw new CellIsEmptyException(id);
        }

        var freePlacesCount = cell.getFreePlacesCount();
        cell.setFreePlacesCount(freePlacesCount + 1);
        if (freePlacesCount == cell.getCELLS_CAPACITY() - 1) {
            cell.setProduct(null);
        }

        return mapper.convertToResponse(cellRepository.save(cell));
    }
}
