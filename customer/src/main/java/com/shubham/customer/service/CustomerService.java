package com.shubham.customer.service;

import com.shubham.customer.entity.Customer;
import com.shubham.customer.repository.CustomerRepository;
import com.shubham.customer.request.CustomerRequest;
import com.shubham.customer.response.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .build();

        customerRepository.saveAndFlush(customer);
        log.info("Customer data saved into CUSTOMER database successfully");

        // check if customer is fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerUuid}",
                FraudCheckResponse.class,
                customer.getCustomerUuid()
        );

        if (fraudCheckResponse.isFraudster()) {
            log.info("Customer is found to be Fraudulent");
            throw new IllegalStateException("Customer is fraudster");
        }
        log.info("Customer is found to be NOT Fraudulent");
    }
}
