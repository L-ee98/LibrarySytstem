package com.example.ms_borrower.enums;

import lombok.Getter;

@Getter
public enum BorrowerError {
    USERNAME_EXIST_ERROR("BRW-00001", "Username exist."),
    BORROWER_NOT_FOUND_ERROR("BRW-00001", "Borrower not found."),
    VALIDATION_ERROR("BRW-00099", "Error while validating input. {}"),
    GENERAL_ERROR("BRW-50000", "Internal server error. Please contact service administrator.");

    private final String code;
    private final String description;

    BorrowerError(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
