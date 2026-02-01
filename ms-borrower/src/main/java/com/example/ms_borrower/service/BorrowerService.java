package com.example.ms_borrower.service;

import com.example.ms_borrower.entity.Borrower;
import com.example.ms_borrower.enums.BorrowerError;
import com.example.ms_borrower.enums.StatusCode;
import com.example.ms_borrower.exception.GenericBadException;
import com.example.ms_borrower.mapper.BorrowerMapper;
import com.example.ms_borrower.model.BorrowLimitRequestDTO;
import com.example.ms_borrower.model.BorrowerRequestDTO;
import com.example.ms_borrower.model.BorrowerResponseDTO;
import com.example.ms_borrower.repository.BorrowerRepository;
import com.example.ms_borrower.util.ValidateUtil;
import com.example.ms_borrower.validate.BorrowerValidateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BorrowerService {
    private final BorrowerMapper borrowerMapper;
    private final BorrowerRepository borrowerRepository;
    private final BorrowerValidateService borrowerValidateService;

    @Transactional
    public BorrowerResponseDTO createBorrower (BorrowerRequestDTO borrowerRequestDTO) {
        log.info("[BorrowerService|createBorrower] Validate borrowerRequestDTO");
        borrowerValidateService.validateBook(borrowerRequestDTO);

        log.info("[BorrowerService|createBorrower] Convert DTO to Entity");
        Borrower borrower = borrowerMapper.toEntity(borrowerRequestDTO);
        borrowerRepository.save(borrower);

        return borrowerMapper.toResponse(borrower);
    }

    public Page<BorrowerResponseDTO> getBorrowerList(Pageable pageable) {
        log.info("[BorrowerService|getBorrowerList] Retrieve borrower list");
        Page<Borrower> bookPage = borrowerRepository.findAllByStatusCode(StatusCode.ACTIVE.getCode(), pageable);
        return bookPage.map(borrowerMapper::toResponse);
    }

    @Transactional
    public void updateBorrowLimit (BorrowLimitRequestDTO borrowLimitRequestDTO) {
        log.info("[BorrowerService|updateBorrowLimit] Validate borrowerLimitRequestDTO");
        ValidateUtil.validate(borrowLimitRequestDTO);

        log.info("[BorrowerService|updateBorrowLimit] Prepare to update borrow limit");
        Borrower borrower = borrowerRepository.findById(borrowLimitRequestDTO.getId()).orElseThrow(() -> new GenericBadException(BorrowerError.BORROWER_NOT_FOUND_ERROR));
        borrower.setBorrowLimitRemaining(borrowLimitRequestDTO.getBorrowLimitRemaining());

        borrowerRepository.save(borrower);
    }
}
