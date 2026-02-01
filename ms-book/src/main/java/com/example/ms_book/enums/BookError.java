package com.example.ms_book.enums;

import lombok.Getter;

@Getter
public enum BookError {
    BOOK_TITLE_MISMATCH("BKE-00001", "Book title does not match with existing ISBN book title."),
    BOOK_AUTHOR_MISMATCH("BKE-00002", "Book Author does not match with existing ISBN book author."),
    BOOK_UNAVAILABLE("BKE-00003", "This book is unavailable."),
    BOOK_NOT_BORROWED("BKE-00004", "Book is not borrowed."),
    BORROW_LIMIT_EXCEED("BKE-00005", "Borrow book limit exceeded {}."),
    VALIDATION_ERROR("BKE-00099", "Error while validating input. {}"),
    GENERAL_ERROR("BKE-50000", "Internal server error. Please contact service administrator.");

    private final String code;
    private final String description;

    BookError(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
