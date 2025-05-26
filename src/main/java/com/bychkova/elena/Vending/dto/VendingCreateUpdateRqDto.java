package com.bychkova.elena.Vending.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class VendingCreateUpdateRqDto {
    private String address;
    private int cellsCount;
    private String status;
    private UUID[] paymentMethodsId;

}
