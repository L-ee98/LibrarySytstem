package com.example.ms_borrower.validate;

import com.example.ms_borrower.entity.Borrower;
import com.example.ms_borrower.enums.BorrowerError;
import com.example.ms_borrower.exception.GenericBadException;
import com.example.ms_borrower.model.BorrowerRequestDTO;
import com.example.ms_borrower.repository.BorrowerRepository;
import com.example.ms_borrower.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowerValidateService {

    private final BorrowerRepository borrowerRepository;

    public void validateBook(BorrowerRequestDTO borrowerRequestDTO){
        ValidateUtil.validate(borrowerRequestDTO);
        Optional<Borrower> borrowerOptional = borrowerRepository.findByUsername(borrowerRequestDTO.getUsername());
        if(borrowerOptional.isPresent()) {
            throw new GenericBadException(BorrowerError.USERNAME_EXIST_ERROR);
        }
    }
}
