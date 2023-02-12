package com.shubham.customer.controller;

import com.shubham.customer.request.CustomerRequest;
import com.shubham.customer.service.CustomerService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.shubham.customer.util.CustomerUtil.getConstraintViolationResponseEntity;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("Registration initiated for customer named {}", customerRequest.getFirstName());
        try {
            customerService.registerCustomer(customerRequest);
            log.info("Registration successful for customer named {}", customerRequest.getFirstName());
            return new ResponseEntity<>("Customer registered successfully", HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            log.error("Registration failed due to {}", e.getLocalizedMessage());
            return getConstraintViolationResponseEntity(e);
        }
    }
}
