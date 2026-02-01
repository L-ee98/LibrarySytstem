package com.example.ms_book.client;

import com.example.ms_book.model.BorrowLimitRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "ms-borrower",
        url = "${borrower.service.url}"
)
public interface BorrowerClient {

    @RequestMapping(method = RequestMethod.PATCH, value = "update-borrow-limit")
    void updateBorrowLimit(
            @RequestBody BorrowLimitRequestDTO borrowLimitRequestDTO
    );
}
