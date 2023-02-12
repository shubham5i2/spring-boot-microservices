package com.shubham.fraud.repository;

import com.shubham.fraud.entity.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, UUID> {
}
