package com.example.ms_book.exception;

import com.example.ms_book.constant.BookCommonConstant;
import com.example.ms_book.enums.BookError;
import com.example.ms_book.model.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(GenericBadException.class)
    public ResponseEntity<ErrorDTO> handleException(GenericBadException genericBadException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(BookCommonConstant.HTTP_STATUS_FAILED);
        errorDTO.setErrorCode(genericBadException.getErrorCode());
        errorDTO.setErrorDescription(genericBadException.getErrorDescription());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception) {
        log.error("Something went wrong", exception);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(BookCommonConstant.HTTP_STATUS_FAILED);
        errorDTO.setErrorCode(BookError.GENERAL_ERROR.getCode());
        errorDTO.setErrorDescription(BookError.GENERAL_ERROR.getDescription());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
