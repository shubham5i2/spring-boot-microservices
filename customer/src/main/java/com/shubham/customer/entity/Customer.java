package com.shubham.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customerUuid;

    @NotEmpty(message = "Firstname cannot be empty")
    @Size(min = 3, message = "Firstname should have least 3 characters")
    private String firstName;

    @NotEmpty(message = "Lastname cannot be empty")
    private String lastName;

    @Email(message = "Please enter a valid email")
    private String email;
}
