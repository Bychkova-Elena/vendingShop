package com.bychkova.elena.Vending.service;

import com.bychkova.elena.Vending.dto.VendingCreateUpdateRqDto;
import com.bychkova.elena.Vending.dto.VendingRsDto;
import com.bychkova.elena.Vending.repository.VendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendingService {

    @Autowired
    private VendingRepository vendingRepository;

    public List<VendingRsDto> getAllVending() {
        return vendingRepository.findAll();
    }

    public Optional<VendingRsDto> getVendingById(Long id) {
        return vendingRepository.findById(id);
    }

    public int createVending(VendingCreateUpdateRqDto vending) {
        return vendingRepository.save(vending);
    }

    public int updateVending(Long id, VendingCreateUpdateRqDto vendingDetails) {
        return vendingRepository.update(id, vendingDetails);
    }

    public void deleteVending(Long id) {
        vendingRepository.deleteById(id);
    }

}
