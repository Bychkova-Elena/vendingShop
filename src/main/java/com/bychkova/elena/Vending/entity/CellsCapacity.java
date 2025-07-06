package com.bychkova.elena.Vending.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "cells_capacity")
public class CellsCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cell_id")
    private Cell cell;

    private int freePlacesCount;
    private final int capacity;

    public CellsCapacity(int capacity) {
        this.capacity = capacity;
        this.freePlacesCount = capacity;
    }

    public CellsCapacity() {
        this.capacity = 20;
    }

    public boolean isFull() {
        return this.freePlacesCount == 0;
    }

    public boolean isEmpty() {
        return this.freePlacesCount == capacity;
    }
}
