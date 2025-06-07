package com.bychkova.elena.Vending.entity;

import jakarta.persistence.*;
import com.bychkova.elena.Vending.enumeration.VendingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vendings")
public class Vending {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 255, nullable = false)
        private String address;
        @Enumerated(EnumType.STRING)
        @Column(length = 20, nullable = false)
        private VendingStatus status;
        private int capacity;

        @OneToMany(mappedBy = "vending", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Cell> cells = new ArrayList<>();

        public Vending(String address, VendingStatus status, int capacity) {
                this.address = address;
                this.status = status;
                this.capacity = capacity;
        }

        public Vending() {
        }

        public void addCell(Cell cell) {
                cell.setVending(this);
                this.cells.add(cell);
        }
}
