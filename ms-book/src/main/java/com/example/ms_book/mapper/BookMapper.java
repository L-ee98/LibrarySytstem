package com.example.ms_book.mapper;

import com.example.ms_book.entity.Book;
import com.example.ms_book.model.BookRequestDTO;
import com.example.ms_book.model.BookResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface BookMapper {
    Book toEntity(BookRequestDTO bookRequestDTO);

    BookResponseDTO toResponse(Book book);
}
