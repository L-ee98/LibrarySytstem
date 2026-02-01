package com.example.ms_borrower.mapper;

import com.example.ms_borrower.entity.Borrower;
import com.example.ms_borrower.model.BorrowerRequestDTO;
import com.example.ms_borrower.model.BorrowerResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface BorrowerMapper {

    @Mapping(target = "borrowLimitRemaining", constant = "5")
    Borrower toEntity(BorrowerRequestDTO bookRequestDTO);

    BorrowerResponseDTO toResponse(Borrower borrower);
}
