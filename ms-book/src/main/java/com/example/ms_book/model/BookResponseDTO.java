package com.example.ms_book.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class BookResponseDTO extends BaseResponseDTO {
    private String isbn;
    private String title;
    private String author;
}
