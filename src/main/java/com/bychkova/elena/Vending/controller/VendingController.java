package com.bychkova.elena.Vending.controller;

import com.bychkova.elena.Vending.dto.VendingResponse;
import com.bychkova.elena.Vending.service.VendingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.bychkova.elena.Vending.dto.VendingRequest;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vending")
public class VendingController {

    private final VendingService vendingService;

    public VendingController(VendingService vendingService) {
        this.vendingService = vendingService;
    }

    @GetMapping
    public Iterable<VendingResponse> getAllVending() {
        return vendingService.getAllVending();
    }

    @GetMapping("/broken")
    public Iterable<VendingResponse> getBrokenVending() {
        return vendingService.getBrokenVending();
    }

    @GetMapping("/{id}")
    public Optional<VendingResponse> getVendingById(@PathVariable Long id) {
        return vendingService.getVendingById(id);
    }

    @PostMapping
    public VendingResponse createVending(@Valid @RequestBody VendingRequest request) {
        return vendingService.createVending(
                request.getAddress(),
                request.getStatus(),
                request.getCapacity()
        );
    }

    @PutMapping("/{id}")
    public Optional<VendingResponse> updateVending(@PathVariable Long id,
                                                         @Valid @RequestBody VendingRequest request) {
        return vendingService.updateVending(id, request.getAddress(), request.getStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteVending(@PathVariable Long id) {
        vendingService.deleteVending(id);
    }
}

