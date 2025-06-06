package com.bychkova.elena.Vending.controller;

import com.bychkova.elena.Vending.entity.Vending;
import com.bychkova.elena.Vending.service.VendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bychkova.elena.Vending.dto.VendingRequest;

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
    public ResponseEntity<Vending> createVending(@RequestBody VendingRequest request) {
        Vending vending = vendingService.createVending(
                request.getAddress(),
                request.getStatus(),
                request.getCapacity()
        );
        return ResponseEntity.ok(vending);
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
