package com.bychkova.elena.Vending.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class addProductInCellRq {
    private Long productId;
    @NotNull
    private int quantity;
}
