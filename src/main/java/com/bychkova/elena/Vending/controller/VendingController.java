package com.bychkova.elena.Vending.controller;

import com.bychkova.elena.Vending.dto.VendingCreateUpdateRqDto;
import com.bychkova.elena.Vending.dto.VendingRsDto;
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
    public List<VendingRsDto> getAllVengings() {
        return vendingService.getAllVending();
    }

    @GetMapping("/{id}")
    public VendingRsDto getVendingById(@PathVariable Long id) {
        return vendingService.getVendingById(id)
                .orElseThrow(() -> new RuntimeException("Vending not found"));
    }

    @PostMapping
    public int createVending(@RequestBody VendingCreateUpdateRqDto vending) {
        return vendingService.createVending(vending);
    }

    @PutMapping("/{id}")
    public int updateVending(@PathVariable Long id, @RequestBody VendingCreateUpdateRqDto vending) {
        return vendingService.updateVending(id, vending);
    }

    @DeleteMapping("/{id}")
    public void deleteVending(@PathVariable Long id) {
        vendingService.deleteVending(id);
    }
}
