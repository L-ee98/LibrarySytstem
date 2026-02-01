package com.example.ms_book.model;

import lombok.Data;

@Data
public class ErrorDTO {
    private String status;
    private String errorCode;
    private String errorDescription;
}
