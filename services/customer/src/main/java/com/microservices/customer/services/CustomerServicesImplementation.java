package com.microservices.customer.services;

import com.microservices.customer.data.models.Customer;
import com.microservices.customer.data.repositories.CustomerRepository;
import com.microservices.customer.dto.requests.CustomerRequests.CustomerRequest;
import com.microservices.customer.dto.responses.CustomerResponse;
import com.microservices.customer.exceptions.CustomerNotFoundException;
import com.microservices.customer.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServicesImplementation implements CustomerService{

    private final CustomerRepository customerRepository;

    private final CustomerMapper mapper;


    @Override
    public String createCustomer(CustomerRequest customerRequest) {
        var Customer = customerRepository.save(mapper.toCustomer(customerRequest));
        return Customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "cannot update customer:: No such customer"
                ));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);

    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsByCustomerId(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    @Override
    public CustomerResponse findByCustomerId(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("customer not registered"));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank((customerRequest.firstname()))){
            customer.setFirstname(customerRequest.firstname());
        }
        if (StringUtils.isNotBlank((customerRequest.lastname()))){
            customer.setLastname(customerRequest.firstname());
        }
        if (StringUtils.isNotBlank((customerRequest.email()))){
            customer.setEmail(customerRequest.email());
        }
        if ((customerRequest.address()!= null)){
            customer.setAddress(customerRequest.address());
        }
    }
}
