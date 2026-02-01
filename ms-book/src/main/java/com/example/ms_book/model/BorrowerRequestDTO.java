package com.example.ms_book.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class BorrowerRequestDTO {
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    private int borrowLimitRemaining;
}
