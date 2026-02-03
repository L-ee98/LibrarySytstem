package com.example.ms_borrower.exception;

import com.example.ms_borrower.enums.BorrowerError;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GenericBadException extends RuntimeException {
    private String errorCode;
    private String errorDescription;

    public GenericBadException(BorrowerError error) {
        super(error.getDescription());
        this.errorCode = error.getCode();
        this.errorDescription = error.getDescription();
    }

    public GenericBadException(String errorCode, String errorDescription) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
