package com.example.ms_book.model;

import lombok.Data;

import java.util.UUID;

@Data
public class BorrowLimitRequestDTO {
    private UUID id;
    private int borrowLimitRemaining;
}
