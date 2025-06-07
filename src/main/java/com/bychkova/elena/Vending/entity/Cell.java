package com.bychkova.elena.Vending.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Setter
@Table(name = "vendings_cells")
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vending_id")
    private Vending vending;

    public Cell() {}
}
