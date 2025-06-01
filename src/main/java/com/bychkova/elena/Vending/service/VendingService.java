package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.entity.Vending;
import com.bychkova.elena.Vending.repository.VendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendingService {

    @Autowired
    private VendingRepository vendingRepository;

    public List<Vending> getAllVendings() {
        return vendingRepository.findAll();
    }

    public Optional<Vending> getVendingById(Long id) {
        return vendingRepository.findById(id);
    }

    public int createVending(Vending vending) {
        return vendingRepository.save(vending);
    }

    public int updateVending(Long id, Vending vendingDetails) {
        return vendingRepository.update(id, vendingDetails);
    }

    public void deleteVending(Long id) {
        vendingRepository.deleteById(id);
    }

}
