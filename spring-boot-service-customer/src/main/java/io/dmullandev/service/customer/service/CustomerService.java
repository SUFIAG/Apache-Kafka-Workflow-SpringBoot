package io.dmullandev.service.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.dmullandev.service.customer.model.Customer;
import io.dmullandev.service.customer.model.CustomerRegistrationRequest;
import io.dmullandev.service.customer.repository.CustomerRepository;
import io.dmullandev.service.customer.response.FraudCheckResponse;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                                    .firstName(request.firstName())
                                    .lastName(request.lastName())
                                    .email(request.email())
                                    .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraudCheck/{customerId}",
                        FraudCheckResponse.class,
                        customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Customer is a fraudster");
        }
    }
}
