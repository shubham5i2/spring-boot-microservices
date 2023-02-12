package com.shubham.fraud.service;

import com.shubham.fraud.entity.FraudCheckHistory;
import com.shubham.fraud.repository.FraudCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudCheckHistoryService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(UUID customerUuid) {
        FraudCheckHistory fraudCheckHistory = FraudCheckHistory.builder()
                .customerUuid(customerUuid)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();

        fraudCheckHistoryRepository.save(fraudCheckHistory);
        log.info("Customer data saved into FRAUD database successfully");
        return false;
    }
}
