package com.example.ms_book.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
    ACTIVE("A", "Active"),
    INACTIVE("I", "Inactive"),
    DELETE("D", "Delete");

    private final String code;
    private final String description;

    StatusCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
