package com.example.ms_book.service;

import com.example.ms_book.entity.Book;
import com.example.ms_book.enums.StatusCode;
import com.example.ms_book.mapper.BookMapper;
import com.example.ms_book.model.BookRequestDTO;
import com.example.ms_book.model.BookResponseDTO;
import com.example.ms_book.repository.BookRepository;
import com.example.ms_book.validate.BookValidateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookValidateService bookValidateService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        bookValidateService.validateBook(bookRequestDTO);

        Book book = bookMapper.toEntity(bookRequestDTO);
        book = bookRepository.save(book);

        return bookMapper.toResponse(book);
    }

    public Page<BookResponseDTO> getBookList(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAllByStatusCode(StatusCode.ACTIVE.getCode(), pageable);
        return bookPage.map(bookMapper::toResponse);
    }
}
