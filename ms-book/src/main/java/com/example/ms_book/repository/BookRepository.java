package com.example.ms_book.repository;

import com.example.ms_book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Page<Book> findAllByStatusCode(String statusCode, Pageable pageable);
    Optional<Book> findFirstByIsbn(String isbn);
    Optional<Book> findByIdAndBorrowFlagAndStatusCode(UUID bookId, boolean reserveBy, String statusCode);
    int countByBorrowBy(UUID borrowerId);
}
