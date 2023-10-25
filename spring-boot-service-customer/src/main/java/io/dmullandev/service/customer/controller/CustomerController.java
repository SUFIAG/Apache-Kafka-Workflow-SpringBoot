package io.dmullandev.service.customer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.dmullandev.service.customer.model.CustomerRegistrationRequest;
import io.dmullandev.service.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("ap1/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("Register customer: {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }
}
