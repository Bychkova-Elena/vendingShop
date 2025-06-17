package com.bychkova.elena.Vending.mapper;

import com.bychkova.elena.Vending.dto.VendingResponse;
import com.bychkova.elena.Vending.entity.Vending;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class VendingMapper {

    public VendingResponse convertToResponse(Vending vending) {
        VendingResponse response = new VendingResponse();
        response.setId(vending.getId());
        response.setAddress(vending.getAddress());
        response.setStatus(vending.getStatus().name());
        response.setCapacity(vending.getCapacity());

        response.setCells(vending.getCells().stream()
                .map(cell -> {
                    VendingResponse.CellResponse cr = new VendingResponse.CellResponse();
                    cr.setId(cell.getId());
                    cr.setVendingId(cell.getVending().getId());
                    if(cell.getProduct() != null) {
                        cr.setProductId(cell.getProduct().getId());
                    }
                    cr.setFreePlacesCount(cell.getFreePlacesCount());
                    return cr;
                })
                .collect(Collectors.toList()));

        return response;
    }

    public Iterable<VendingResponse> convertListToResponse(Iterable<Vending> vendings) {
        ArrayList<VendingResponse> vengingList = new ArrayList<>();

        for (Vending v : vendings) {
            vengingList.add(convertToResponse(v));
        }

        return vengingList;
    }
}
