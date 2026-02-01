package com.example.ms_book.service;

import com.example.ms_book.client.BorrowerClient;
import com.example.ms_book.entity.Book;
import com.example.ms_book.enums.BookError;
import com.example.ms_book.enums.StatusCode;
import com.example.ms_book.exception.GenericBadException;
import com.example.ms_book.mapper.BookMapper;
import com.example.ms_book.model.BookResponseDTO;
import com.example.ms_book.model.BorrowBookDTO;
import com.example.ms_book.model.BorrowLimitRequestDTO;
import com.example.ms_book.model.ReturnBookDTO;
import com.example.ms_book.repository.BookRepository;
import com.example.ms_book.util.ValidateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BorrowService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BorrowerClient borrowerClient;

    @Value("${borrow.limit}")
    private int borrowLimit;

    @Transactional
    public BookResponseDTO borrowBook(BorrowBookDTO borrowBookDTO) {
        log.info("[BorrowService|borrowBook] Validate borrowBookDTO");
        ValidateUtil.validate(borrowBookDTO);

        log.info("[BorrowService|borrowBook] Find the book to borrow");
        Book book = bookRepository.findByIdAndBorrowFlagAndStatusCode(borrowBookDTO.getBookId(), false, StatusCode.ACTIVE.getCode())
                .orElseThrow(() -> new GenericBadException(BookError.BOOK_UNAVAILABLE));
        book.setBorrowBy(borrowBookDTO.getBorrowerId());
        book.setBorrowFlag(true);
        bookRepository.saveAndFlush(book);

        int numOfBookReserveByBorrower = bookRepository.countByBorrowBy(borrowBookDTO.getBorrowerId());
        log.info("[BorrowService|borrowBook] Number of book reserved by borrower is: {}", numOfBookReserveByBorrower);

        if (numOfBookReserveByBorrower > borrowLimit) {
            throw new GenericBadException(BookError.BORROW_LIMIT_EXCEED.getCode(), BookError.BORROW_LIMIT_EXCEED.getDescription().replace("{}", String.valueOf(borrowLimit)));
        }

        log.info("[BorrowService|borrowBook] Prepare to update borrower limit remaining");
        BorrowLimitRequestDTO borrowLimitRequestDTO = new BorrowLimitRequestDTO();
        borrowLimitRequestDTO.setId(borrowBookDTO.getBorrowerId());
        borrowLimitRequestDTO.setBorrowLimitRemaining(borrowLimit-numOfBookReserveByBorrower);
        borrowerClient.updateBorrowLimit(borrowLimitRequestDTO);
        log.info("[BorrowService|borrowBook] Successfully updated borrower limit remaining");

        return bookMapper.toResponse(book);
    }

    @Transactional
    public BookResponseDTO returnBook(ReturnBookDTO returnBookDTO) {
        log.info("[BorrowService|returnBook] Validate returnBookDTO");
        ValidateUtil.validate(returnBookDTO);

        log.info("[BorrowService|returnBook] Find the book to return");
        Book book = bookRepository.findByIdAndBorrowFlagAndStatusCode(returnBookDTO.getBookId(), true, StatusCode.ACTIVE.getCode())
                .orElseThrow(() -> new GenericBadException(BookError.BOOK_NOT_BORROWED));
        UUID borrowerId = book.getBorrowBy();

        book.setBorrowFlag(false);
        book.setBorrowBy(null);
        bookRepository.saveAndFlush(book);

        int numOfBookReserveByBorrower = bookRepository.countByBorrowBy(borrowerId);

        log.info("[BorrowService|returnBook] Prepare to update borrower limit remaining");
        BorrowLimitRequestDTO borrowLimitRequestDTO = new BorrowLimitRequestDTO();
        borrowLimitRequestDTO.setId(borrowerId);
        borrowLimitRequestDTO.setBorrowLimitRemaining(borrowLimit-numOfBookReserveByBorrower);
        borrowerClient.updateBorrowLimit(borrowLimitRequestDTO);
        log.info("[BorrowService|returnBook] Successfully updated borrower limit remaining");

        return bookMapper.toResponse(book);
    }
}
