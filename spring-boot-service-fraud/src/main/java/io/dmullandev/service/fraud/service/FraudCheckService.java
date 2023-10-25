package io.dmullandev.service.fraud.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import io.dmullandev.service.fraud.model.FraudCheckHistory;
import io.dmullandev.service.fraud.repository.FraudCheckHistoryRepository;

@Service
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                        FraudCheckHistory.builder()
                                         .id(customerId)
                                         .isFraudster(false)
                                         .createdAt(LocalDateTime.now())
                                         .build());
        return false;
    }
}
