package com.example.ms_book.controller;

import com.example.ms_book.model.BookRequestDTO;
import com.example.ms_book.model.BookResponseDTO;
import com.example.ms_book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/book/")
public class BookController {

    private final BookService bookService;

    @PostMapping("create")
    public ResponseEntity<BookResponseDTO> createBook (
            @RequestBody BookRequestDTO bookRequest
    ) {
        log.info("[BookController|createBook] Request: {}", bookRequest.toString());
        BookResponseDTO bookResponseDTO = bookService.createBook(bookRequest);
        log.info("[BookController|createBook] Response: {}", bookResponseDTO.toString());
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Page<BookResponseDTO>> getBookList (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") Sort.Direction sortOrder
    ) {
        log.info("[BookController|getBookList] Page: {}, Size: {}, SortBy: {}, SortOrder: {}", page, size, sortBy, sortOrder);
        Sort sort = Sort.by (sortOrder, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BookResponseDTO> response = bookService.getBookList(pageable);
        log.info("[BookController|getBookList] Response: {}", response.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
