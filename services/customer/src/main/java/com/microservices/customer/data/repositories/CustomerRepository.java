package com.microservices.customer.data.repositories;

import com.microservices.customer.data.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
