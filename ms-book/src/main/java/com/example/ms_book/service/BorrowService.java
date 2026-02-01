package com.example.ms_book.service;

import com.example.ms_book.client.BorrowerClient;
import com.example.ms_book.entity.Book;
import com.example.ms_book.enums.BookError;
import com.example.ms_book.enums.StatusCode;
import com.example.ms_book.exception.GenericBadException;
import com.example.ms_book.mapper.BookMapper;
import com.example.ms_book.model.BookResponseDTO;
import com.example.ms_book.model.BorrowBookDTO;
import com.example.ms_book.model.BorrowerRequestDTO;
import com.example.ms_book.repository.BookRepository;
import com.example.ms_book.util.ValidateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BorrowService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BorrowerClient borrowerClient;

    @Transactional
    public BookResponseDTO borrowBook(BorrowBookDTO borrowBookDTO) {
        ValidateUtil.validate(borrowBookDTO);

        Book book = bookRepository.findByIdAndReserveFlagAndStatusCode(borrowBookDTO.getBookId(), false, StatusCode.ACTIVE.getCode())
                .orElseThrow(() -> new GenericBadException(BookError.BOOK_UNAVAILABLE));
        book.setReserveBy(borrowBookDTO.getBorrowerId());
        book.setReserveFlag(true);
        bookRepository.saveAndFlush(book);

        int numOfBookReserveByBorrower = bookRepository.countByReserveBy(borrowBookDTO.getBorrowerId());
        if (numOfBookReserveByBorrower >= 5) {
            throw new GenericBadException(BookError.BORROW_LIMIT_EXCEED);
        }

        BorrowerRequestDTO borrowerRequestDTO = new BorrowerRequestDTO();
        borrowerRequestDTO.setId(borrowBookDTO.getBorrowerId());
        borrowerRequestDTO.setBorrowLimitRemaining(5-numOfBookReserveByBorrower);
        borrowerClient.updateBorrowLimit(borrowerRequestDTO);

        return bookMapper.toResponse(book);
    }

    @Transactional
    public BookResponseDTO returnBook(BorrowBookDTO borrowBookDTO) {
        ValidateUtil.validate(borrowBookDTO);

        Book book = bookRepository.findByIdAndReserveFlagAndStatusCode(borrowBookDTO.getBookId(), true, StatusCode.ACTIVE.getCode())
                .orElseThrow(() -> new GenericBadException(BookError.BOOK_NOT_FOUND));
        book.setReserveFlag(false);
        book.setReserveBy(null);
        bookRepository.saveAndFlush(book);

        int numOfBookReserveByBorrower = bookRepository.countByReserveBy(book.getReserveBy());

        BorrowerRequestDTO borrowerRequestDTO = new BorrowerRequestDTO();
        borrowerRequestDTO.setId(borrowBookDTO.getBorrowerId());
        borrowerRequestDTO.setBorrowLimitRemaining(5-numOfBookReserveByBorrower);
        borrowerClient.updateBorrowLimit(borrowerRequestDTO);

        return bookMapper.toResponse(book);
    }
}
