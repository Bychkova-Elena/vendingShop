package com.bychkova.elena.Vending.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "vendings_cells")
public class Cell {
    private final int CELLS_CAPACITY = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vending_id")
    private Vending vending;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int freePlacesCount;

    public Cell() {
        this.freePlacesCount = CELLS_CAPACITY;
        this.product = null;
    }

    public boolean isFull() {
        return this.freePlacesCount == 0;
    }
}
