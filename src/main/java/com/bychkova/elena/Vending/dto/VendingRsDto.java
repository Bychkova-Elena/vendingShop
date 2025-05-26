package com.bychkova.elena.Vending.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@Builder
public class VendingRsDto {
    private UUID id;
    private String address;
    private int cellsCount;
    private String status;
    private ArrayList<UUID> paymentMethodsId;
}