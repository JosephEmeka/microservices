package com.microservices.customer.controllers;

import com.microservices.customer.dto.requests.CustomerRequests.CustomerRequest;
import com.microservices.customer.dto.responses.CustomerResponse;
import com.microservices.customer.services.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerServiceimpl;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {

        return ResponseEntity.ok(customerServiceimpl.createCustomer(customerRequest));

    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        customerServiceimpl.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerServiceimpl.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerServiceimpl.existsByCustomerId(customerId));
    }


    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerServiceimpl.findByCustomerId(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("customer-id") String customerId
    ) {
        customerServiceimpl.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
