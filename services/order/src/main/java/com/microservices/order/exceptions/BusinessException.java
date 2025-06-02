package com.microservices.order.exceptions;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final String msg;

}
