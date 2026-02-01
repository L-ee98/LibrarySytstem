package com.example.ms_book.exception;

import com.example.ms_book.enums.BookError;
import lombok.Getter;

@Getter
public class GenericBadException extends RuntimeException {
    private String errorCode;
    private String errorDescription;

    public GenericBadException() {
    }

    public GenericBadException(String message) {
        super(message);
    }

    public GenericBadException(BookError error) {
        super(error.getDescription());
        this.errorCode = error.getCode();
        this.errorDescription = error.getDescription();
    }

    public GenericBadException(String errorCode, String errorDescription) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public GenericBadException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericBadException(Throwable cause) {
        super(cause);
    }

    public GenericBadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
