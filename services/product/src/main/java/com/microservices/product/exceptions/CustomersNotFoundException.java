package com.microservices.product.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomersNotFoundException extends RuntimeException {

    private final String msg;

}
