package com.example.ms_book.validate;

import com.example.ms_book.entity.Book;
import com.example.ms_book.enums.BookError;
import com.example.ms_book.exception.GenericBadException;
import com.example.ms_book.model.BookRequestDTO;
import com.example.ms_book.repository.BookRepository;
import com.example.ms_book.util.ValidateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class BookValidateService {

    private final BookRepository bookRepository;

    public void validateBook(BookRequestDTO bookRequestDTO){
        ValidateUtil.validate(bookRequestDTO);
        Optional<Book> bookOptional = bookRepository.findFirstByIsbn(bookRequestDTO.getIsbn());
        if(bookOptional.isPresent()) {
            if(!bookOptional.get().getTitle().equals(bookRequestDTO.getTitle())) {
                throw new GenericBadException(BookError.BOOK_TITLE_MISMATCH);
            }
            if(!bookOptional.get().getAuthor().equals(bookRequestDTO.getAuthor())) {
                throw new GenericBadException(BookError.BOOK_AUTHOR_MISMATCH);
            }
        }
    }
}
