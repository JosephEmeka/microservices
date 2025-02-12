package com.microservices.customer.dto.responses;


import com.microservices.customer.data.models.Address;

public record CustomerResponse (String id,
                                String firstname,
                                String lastname,
                                String email,
                                Address address
                                ){

}
