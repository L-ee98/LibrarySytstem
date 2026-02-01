package com.example.ms_book.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class BookRequestDTO {
    @NotBlank
    @ISBN
    private String isbn;
    @NotBlank
    private String author;
    @NotBlank
    private String title;
}
