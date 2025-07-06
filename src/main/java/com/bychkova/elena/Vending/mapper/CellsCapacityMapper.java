package com.bychkova.elena.Vending.mapper;

import com.bychkova.elena.Vending.dto.CellsCapacityResponse;
import com.bychkova.elena.Vending.entity.CellsCapacity;
import org.springframework.stereotype.Component;

@Component
public class CellsCapacityMapper {

    public CellsCapacityResponse convertToResponse(CellsCapacity c) {
        CellsCapacityResponse cellsFreePlacesCountResponse = new CellsCapacityResponse();
        cellsFreePlacesCountResponse.setId(c.getId());
        cellsFreePlacesCountResponse.setCellId(c.getCell().getId());
        cellsFreePlacesCountResponse.setFreePlacesCount(c.getFreePlacesCount());
        cellsFreePlacesCountResponse.setCapacity(c.getCapacity());

        return cellsFreePlacesCountResponse;
    }
}
