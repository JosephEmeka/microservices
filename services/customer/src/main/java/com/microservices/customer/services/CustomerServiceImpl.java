package com.microservices.customer.services;

import com.microservices.customer.dto.requests.CustomerRequests.CustomerRequest;
import com.microservices.customer.dto.responses.CustomerResponse;

import java.util.List;

public interface CustomerServiceImpl {

    String createCustomer(CustomerRequest customerRequest);

    void updateCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> findAllCustomers();

    Boolean existsByCustomerId(String customerId);

    CustomerResponse findByCustomerId(String customerId);

    void deleteCustomer(String customerId);
}
