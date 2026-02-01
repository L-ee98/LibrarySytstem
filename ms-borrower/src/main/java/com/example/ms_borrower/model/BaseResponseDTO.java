package com.example.ms_borrower.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BaseResponseDTO {
    private UUID id;
    private LocalDateTime createdTime;
    private String createdBy;
    private LocalDateTime updatedTime;
    private String updatedBy;
    private String statusCode;
}
