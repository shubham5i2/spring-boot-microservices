package com.shubham.fraud.controller;

import com.shubham.fraud.response.FraudCheckResponse;
import com.shubham.fraud.service.FraudCheckHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
@Slf4j
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping("{customerUuid}")
    public FraudCheckResponse isFraudster(@PathVariable UUID customerUuid) {
        log.info("Fraud check initiated for customer with id {}", customerUuid);
        boolean fraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerUuid);
        return new FraudCheckResponse(fraudulentCustomer);
    }
}
