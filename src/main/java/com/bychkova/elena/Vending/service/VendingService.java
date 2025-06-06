package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.entity.Cell;
import com.bychkova.elena.Vending.entity.Vending;
import com.bychkova.elena.Vending.enumeration.VendingStatus;
import com.bychkova.elena.Vending.repository.CellRepository;
import com.bychkova.elena.Vending.repository.VendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class VendingService {

    @Autowired
    private VendingRepository vendingRepository;

    @Autowired
    private CellRepository cellRepository;

    public Iterable<Vending> getAllVending() {
        return vendingRepository.findAll();
    }

    public Vending getVendingById(Long id) {
        return vendingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vending not found"));
    }

    public Iterable<Vending> getBrokenVending() {
        Iterable<Vending> vendings = vendingRepository.findAll();
        Iterator <Vending> vendingsIterator = vendings.iterator();

        while (vendingsIterator.hasNext()) {
            Vending element = vendingsIterator.next();
            if (!element.getStatus().equals(VendingStatus.BROKEN)) {
                vendingsIterator.remove();
            }
        }
        return vendings;
    }

    @Transactional
    public Vending createVending(String address, VendingStatus status, int capacity) {
        Vending vending = new Vending(address, status, capacity);
        vending = vendingRepository.save(vending);

        int VENDINGS_CELLS_COUNT = 25;
        Vending finalVending = vending;
        List<Cell> cells = IntStream.range(0, VENDINGS_CELLS_COUNT)
                .mapToObj(i -> {
                    Cell cell = new Cell();
                    cell.setVending(finalVending);
                    return cell;
                })
                .collect(Collectors.toList());

        vending.setCells(cells);
        return vendingRepository.save(vending);
    }

    public Vending updateVending(Long id, String address, VendingStatus status) {
        return vendingRepository.findById(id)
                .map(vending -> {
                    vending.setAddress(address);
                    vending.setStatus(status);
                    return vendingRepository.save(vending);
                })
                .orElseThrow(() -> new RuntimeException("Vending not found"));
    }

    public void deleteVending(Long id) {
        vendingRepository.deleteById(id);
    }

}
