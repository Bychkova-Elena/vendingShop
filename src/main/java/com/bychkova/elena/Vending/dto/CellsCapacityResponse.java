package com.bychkova.elena.Vending.dto;

import lombok.Data;

@Data
public class CellsCapacityResponse {
    private Long id;
    private Long cellId;
    private int freePlacesCount;
    private int capacity;
}
