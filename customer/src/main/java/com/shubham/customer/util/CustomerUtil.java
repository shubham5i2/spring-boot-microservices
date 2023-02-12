package com.shubham.customer.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

public class CustomerUtil {

    public static ResponseEntity<Set<String>> getConstraintViolationResponseEntity(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        Set<String> messages = new HashSet<>(violations.size());
        messages.addAll(violations.stream()
                .map(constraintViolation -> String.format("%s", constraintViolation.getMessage()))
                .toList());
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }
}
