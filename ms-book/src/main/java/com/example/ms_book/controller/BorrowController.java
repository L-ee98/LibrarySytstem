package com.example.ms_book.controller;

import com.example.ms_book.model.BookResponseDTO;
import com.example.ms_book.model.BorrowBookDTO;
import com.example.ms_book.service.BorrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(name = "/v1/")
public class BorrowController {

    private final BorrowService borrowService;

    @PatchMapping ("borrow")
    public ResponseEntity<BookResponseDTO> borrowBook (
            @RequestBody BorrowBookDTO borrowBookDTO
    ) {
        log.info("[BorrowController|borrowBook] Request: {}", borrowBookDTO.toString());
        BookResponseDTO response = borrowService.borrowBook(borrowBookDTO);
        log.info("[BorrowController|borrowBook] Response: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("borrow/return")
    public ResponseEntity<BookResponseDTO> returnBook (
            @RequestBody BorrowBookDTO borrowBookDTO
    ) {
        log.info("[BorrowController|returnBook] Request: {}", borrowBookDTO.toString());
        BookResponseDTO response = borrowService.returnBook(borrowBookDTO);
        log.info("[BorrowController|returnBook] Response: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
