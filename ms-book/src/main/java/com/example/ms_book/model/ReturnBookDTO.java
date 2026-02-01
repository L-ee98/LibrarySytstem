package com.example.ms_book.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ReturnBookDTO {
    @NotNull
    private UUID bookId;
}
