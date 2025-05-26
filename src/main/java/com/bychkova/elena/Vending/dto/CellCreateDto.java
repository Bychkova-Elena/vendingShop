package com.bychkova.elena.Vending.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public class CellCreateDto {
    private UUID id;
    private UUID vendingId;
    private String name;
    private int capacity;
    private int productCount;
}
