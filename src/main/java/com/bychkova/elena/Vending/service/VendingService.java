package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.dto.VendingResponse;
import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.entity.Vending;
import com.bychkova.elena.Vending.enumeration.VendingStatus;
import com.bychkova.elena.Vending.mapper.VendingMapper;
import com.bychkova.elena.Vending.repository.CellRepository;
import com.bychkova.elena.Vending.repository.VendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class VendingService {

    @Autowired
    private VendingRepository vendingRepository;

    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private VendingMapper mapper;

    public Iterable<VendingResponse> getAllVending() {
        return mapper.convertListToResponse(vendingRepository.findAll());
    }

    public Optional<VendingResponse> getVendingById(Long id) {
        var vending = vendingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vending not found"));

        return Optional.ofNullable(mapper.convertToResponse(vending));
    }

    public Iterable<VendingResponse> getBrokenVending() {
        Iterable<Vending> vendings = vendingRepository.findAll();
        Iterator <Vending> vendingsIterator = vendings.iterator();

        while (vendingsIterator.hasNext()) {
            Vending element = vendingsIterator.next();
            if (!element.getStatus().equals(VendingStatus.BROKEN)) {
                vendingsIterator.remove();
            }
        }
        return mapper.convertListToResponse(vendings);
    }

    public VendingResponse createVending(String address, VendingStatus status, int capacity) {
        int VENDINGS_CELLS_COUNT = 25;

        Vending vending = new Vending(address, status, capacity);
        vending = vendingRepository.save(vending);

        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < VENDINGS_CELLS_COUNT; i++) {
            Cell cell = new Cell();
            cells.add(cell);
        }

        Iterable <Cell> IterableCells = cellRepository.saveAll(cells);
        vending.addCells(IterableCells);

        return mapper.convertToResponse(vendingRepository.save(vending));
    }

    public Optional<VendingResponse> updateVending(Long id, String address, VendingStatus status) {
        var v = vendingRepository.findById(id)
                .map(vending -> {
                    vending.setAddress(address);
                    vending.setStatus(status);
                    return vendingRepository.save(vending);
                })
                .orElseThrow(() -> new RuntimeException("Vending not found"));

        return Optional.ofNullable(mapper.convertToResponse(v));
    }

    public void deleteVending(Long id) {
        vendingRepository.deleteById(id);
    }

}
