package com.example.ms_borrower.controller;

import com.example.ms_borrower.model.BorrowerRequestDTO;
import com.example.ms_borrower.model.BorrowerResponseDTO;
import com.example.ms_borrower.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/borrower/")
public class BorrowerController {

    private final BorrowerService borrowerService;

    @PostMapping("create")
    public ResponseEntity<BorrowerResponseDTO> createBorrower (
            @RequestBody BorrowerRequestDTO borrowerRequestDTO
            ) {
        log.info("[BorrowerController|createBorrower] Request: {}", borrowerRequestDTO.toString());
        BorrowerResponseDTO response = borrowerService.createBorrower(borrowerRequestDTO);
        log.info("[BorrowerController|createBorrower] Response: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Page<BorrowerResponseDTO>> getBorrowerList (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "username") String sortBy,
            @RequestParam(defaultValue = "asc") Sort.Direction sortOrder
    ) {
        log.info("[BorrowerController|getBorrowerList] Page: {}, Size: {}, SortBy: {}, SortOrder: {}", page, size, sortBy, sortOrder);
        Sort sort = Sort.by (sortOrder, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BorrowerResponseDTO> response = borrowerService.getBorrowerList(pageable);
        log.info("[BorrowerController|getBorrowerList] Response: {}", response.getContent());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("update-borrow-limit")
    public ResponseEntity<Void> updateBorrowLimit (
            @RequestBody BorrowerRequestDTO borrowerRequestDTO
    ) {
        log.info("[BorrowerController|updateBorrowLimit] Request: {}", borrowerRequestDTO.toString());
        borrowerService.updateBorrowLimit(borrowerRequestDTO);
        log.info("[BorrowerController|updateBorrowLimit] Successfully updated borrow limit.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
