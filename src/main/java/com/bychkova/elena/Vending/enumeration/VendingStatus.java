package com.bychkova.elena.Vending.enumeration;

public enum VendingStatus {
    ACTIVE("Активен"),
    NOT_ACTIVE("Не активен"),
    BROKEN("Сломан");

    private String description;

    VendingStatus(String description) {
        this.description = description;
    }
}