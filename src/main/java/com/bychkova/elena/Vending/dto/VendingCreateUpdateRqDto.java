package com.bychkova.elena.Vending.dto;

import com.bychkova.elena.Vending.enumeration.VendingStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VendingCreateUpdateRqDto {
    private String address;
    private VendingStatus status;
    private int capacity;
}
