package com.bychkova.elena.Vending.entity;

import jakarta.persistence.*;
import com.bychkova.elena.Vending.enumeration.VendingStatus;

@Entity
@Table(name = "vendings")
public class Vending {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String address;
        @Enumerated(EnumType.STRING)
        private VendingStatus status;
        private int capacity;

        public Vending() {}
}
