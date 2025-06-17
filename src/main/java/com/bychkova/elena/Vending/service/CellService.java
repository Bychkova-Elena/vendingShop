package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.entity.Product;
import com.bychkova.elena.Vending.repository.CellRepository;
import com.bychkova.elena.Vending.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CellService {

    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Cell> getAllCells() {
        return cellRepository.findAll();
    }

    public Cell putProductInCell(Long id, Long productId, int quantity) {
        Cell cell = cellRepository.findById(id).orElseThrow(() -> new RuntimeException("Cell not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        boolean isFits = cell.getFreePlacesCount() - quantity >= 0;

        if (cell.isFull() || !isFits) {
            throw new RuntimeException("There is not enough free space in the cell. There are %d free spaces in cell %d."
                    .formatted(cell.getFreePlacesCount(), id));
        }

        cell.setProduct(product);
        cell.setFreePlacesCount(cell.getFreePlacesCount() - quantity);

        return cellRepository.save(cell);
    }

    public Cell decreaseProductInCell(Long id) {
        Cell cell = cellRepository.findById(id).orElseThrow(() -> new RuntimeException("Cell not found"));

        if (cell.isEmpty()) {
            throw new RuntimeException("Cell is Empty");
        }

        var freePlacesCount = cell.getFreePlacesCount();
        cell.setFreePlacesCount(freePlacesCount + 1);
        if (freePlacesCount == cell.getCELLS_CAPACITY() - 1) {
            cell.setProduct(null);
        }

        return cellRepository.save(cell);
    }
}
