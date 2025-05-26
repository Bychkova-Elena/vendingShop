package com.bychkova.elena.Vending.controllers;

import com.bychkova.elena.Vending.dto.VendingCreateUpdateRqDto;
import com.bychkova.elena.Vending.dto.VendingRsDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vending")
public class VendingController {

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public VendingRsDto createVending(@RequestBody VendingCreateUpdateRqDto dto) {
        final int cellsCount = dto.getCellsCount();
        final UUID vendingId = UUID.randomUUID();

        //Количество ячеек задается в запросе
        //Все ячейки должны быть созданы с нулевым количеством продуктов и вместимостью 10
//        for (int i = 0; i < cellsCount; i++) {
//            CellCreateDto.builder()
//                    .id(UUID.randomUUID())
//                    .vendingId(vendingId)
//                    .name("Cell " + (i + 1))
//                    .capacity(10)
//                    .productCount(0)
//                    .build();
//
//        }

        return VendingRsDto.builder()
                .id(vendingId)
                .cellsCount(cellsCount)
                .address(dto.getAddress())
                .status(dto.getStatus())
                .paymentMethodsId(dto.getPaymentMethodsId())
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendingRsDto deleteVendingById(@PathVariable("id") UUID id) {
        return VendingRsDto.builder()
                .id(id)
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendingRsDto updateVendingById(@PathVariable("id") UUID id, @RequestBody VendingCreateUpdateRqDto dto) {
        return VendingRsDto.builder()
                .id(id)
                .cellsCount(dto.getCellsCount())
                .address(dto.getAddress())
                .status(dto.getStatus())
                .paymentMethodsId(dto.getPaymentMethodsId())
                .build();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendingRsDto getVendingById(@PathVariable("id") UUID id) {
        return VendingRsDto.builder()
                .id(id)
                .address("address")
                .cellsCount(20)
                .status("BROKEN")
                .paymentMethodsId(new ArrayList<>(List.of(UUID.randomUUID(), UUID.randomUUID())))
                .build();

    }

    @GetMapping("")
    public ArrayList<VendingRsDto> getVendingByStatus(@RequestParam("status") String status) {

        return new ArrayList<>(List.of(VendingRsDto.builder()
                .id(UUID.randomUUID())
                .status(status)
                .address("")
                .paymentMethodsId(new ArrayList<>(List.of(UUID.randomUUID())))
                .build()));
    }
}
