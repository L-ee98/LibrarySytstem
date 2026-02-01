package com.example.ms_borrower.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class BorrowerResponseDTO extends BaseResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private int maxBorrowLimit;
}
