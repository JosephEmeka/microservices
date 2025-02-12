package com.microservices.customer.dto.requests.CustomerRequests;

import com.microservices.customer.data.models.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message ="customer  first name is required")
        String firstname,
        @NotNull(message ="customer  last name is required")
        String lastname,
        @NotNull(message ="customer cannot be null")
        @Email(message ="Email must be a valid Email")
        String email,
        Address address
) {


}
