package com.example.ms_borrower.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class BorrowerRequestDTO {
    private UUID id;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    private int borrowLimitRemaining;
}
