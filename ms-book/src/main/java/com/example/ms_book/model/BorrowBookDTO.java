package com.example.ms_book.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class BorrowBookDTO {
    @NotNull
    private UUID bookId;
    @NotNull
    private UUID borrowerId;
}
