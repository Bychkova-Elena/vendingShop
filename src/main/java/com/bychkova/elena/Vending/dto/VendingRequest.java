package com.bychkova.elena.Vending.dto;

import com.bychkova.elena.Vending.enumeration.VendingStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VendingRequest {
    @NotBlank
    private String address;

    @NotNull
    private VendingStatus status;

    @Min(1)
    private int capacity;
}
