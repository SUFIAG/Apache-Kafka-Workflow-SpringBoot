package io.dmullandev.service.fraud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.dmullandev.service.fraud.responses.FraudCheckResponse;
import io.dmullandev.service.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/fraudCheck")
@Slf4j
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Fraud check request for customerId {}", customerId);
        return new FraudCheckResponse(fraudCheckService.isFraudulentCustomer(customerId));
    }
}
