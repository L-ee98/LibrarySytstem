package com.example.ms_borrower.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class BorrowLimitRequestDTO {
    @NotNull
    private UUID id;
    @NotNull
    private int borrowLimitRemaining;
}
