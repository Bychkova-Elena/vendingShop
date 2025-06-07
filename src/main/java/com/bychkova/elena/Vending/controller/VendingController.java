package com.bychkova.elena.Vending.controller;

import com.bychkova.elena.Vending.dto.VendingResponse;
import com.bychkova.elena.Vending.entity.Vending;
import com.bychkova.elena.Vending.service.VendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bychkova.elena.Vending.dto.VendingRequest;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vending")
@RequiredArgsConstructor
public class VendingController {

    @Autowired
    private VendingService vendingService;

    @GetMapping
    public Iterable<Vending> getAllVending() {
        return vendingService.getAllVending();
    }

    @GetMapping("/broken")
    public Iterable<Vending> getBrokenVending() {
        return vendingService.getBrokenVending();
    }

    @GetMapping("/{id}")
    public Vending getVendingById(@PathVariable Long id) {
        return vendingService.getVendingById(id);
    }

    @PostMapping
    public ResponseEntity<VendingResponse> createVending(@RequestBody VendingRequest request) {
        Vending vending = vendingService.createVending(
                request.getAddress(),
                request.getStatus(),
                request.getCapacity()
        );

        VendingResponse response = convertToResponse(vending);
        return ResponseEntity.ok(response);
    }

    private VendingResponse convertToResponse(Vending vending) {
        VendingResponse response = new VendingResponse();
        response.setId(vending.getId());
        response.setAddress(vending.getAddress());
        response.setStatus(vending.getStatus().name());
        response.setCapacity(vending.getCapacity());

        response.setCells(vending.getCells().stream()
                .map(cell -> {
                    VendingResponse.CellResponse cr = new VendingResponse.CellResponse();
                    cr.setId(cell.getId());
                    cr.setVendingId(cell.getVending().getId());
                    return cr;
                })
                .collect(Collectors.toList()));

        return response;
    }

    @PutMapping("/{id}")
    public Vending updateVending(@PathVariable Long id,
                                 @RequestBody Vending request) {
        return vendingService.updateVending(id, request.getAddress(), request.getStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteVending(@PathVariable Long id) {
        vendingService.deleteVending(id);
    }
}

