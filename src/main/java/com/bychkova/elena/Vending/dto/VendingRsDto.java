package com.bychkova.elena.Vending.dto;

import com.bychkova.elena.Vending.enumeration.VendingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VendingRsDto {
    private Long id;
    private String address;
    private VendingStatus status;
    private int capacity;
}