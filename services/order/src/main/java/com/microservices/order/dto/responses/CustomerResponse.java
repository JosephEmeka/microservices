package com.microservices.order.dto.responses;

public record CustomerResponse(
        String id,
        String firstname,
        String lastName,
        String email
) {
}
