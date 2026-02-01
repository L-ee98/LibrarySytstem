package com.example.ms_borrower.repository;

import com.example.ms_borrower.entity.Borrower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, UUID> {
    Optional<Borrower> findByUsername(String username);
    Page<Borrower> findAllByStatusCode(String statusCode, Pageable pageable);
}
