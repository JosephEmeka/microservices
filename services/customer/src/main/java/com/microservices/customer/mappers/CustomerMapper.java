package com.microservices.customer.mappers;

import com.microservices.customer.dto.requests.CustomerRequests.CustomerRequest;
import com.microservices.customer.dto.responses.CustomerResponse;
import com.microservices.customer.data.models.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .id(customerRequest.id())
                .firstname(customerRequest.firstname())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress());
    }
}
