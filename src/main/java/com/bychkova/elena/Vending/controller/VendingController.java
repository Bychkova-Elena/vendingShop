package com.bychkova.elena.Vending.controller;

import com.bychkova.elena.Vending.entity.Vending;
import com.bychkova.elena.Vending.service.VendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vending")
public class VendingController {

    @Autowired
    private VendingService vendingService;

    @GetMapping
    public List<Vending> getAllUsers() {
        return vendingService.getAllVendings();
    }

    @GetMapping("/{id}")
    public Vending getVendingById(@PathVariable Long id) {
        return vendingService.getVendingById(id)
                .orElseThrow(() -> new RuntimeException("Vending not found"));
    }

    @PostMapping
    public int createVending(@RequestBody Vending vending) {
        return vendingService.createVending(vending);
    }

    @PutMapping("/{id}")
    public int updateVending(@PathVariable Long id, @RequestBody Vending vending) {
        return vendingService.updateVending(id, vending);
    }

    @DeleteMapping("/{id}")
    public void deleteVending(@PathVariable Long id) {
        vendingService.deleteVending(id);
    }
}
