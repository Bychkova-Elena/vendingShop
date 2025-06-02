package com.bychkova.elena.Vending.entity;

import jakarta.persistence.*;
import com.bychkova.elena.Vending.enumeration.VendingStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
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

        public Vending(Long id, String address, VendingStatus status, int capacity) {
                this.id = id;
                this.address = address;
                this.status = status;
                this.capacity = capacity;
        }
}
