package com.example.ms_borrower.model;

import lombok.Data;

@Data
public class ErrorDTO {
    private String status;
    private String errorCode;
    private String errorDescription;
}
