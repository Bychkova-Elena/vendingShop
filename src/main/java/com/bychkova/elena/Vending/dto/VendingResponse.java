package com.bychkova.elena.Vending.dto;

import lombok.Data;

import java.util.List;

@Data
public class VendingResponse {
    private Long id;
    private String address;
    private String status;
    private int capacity;
    private List<CellResponse> cells;

    @Data
    public static class CellResponse {
        private Long id;
        private Long vendingId;
        private Long productId;
        private int freePlacesCount;
    }
}
