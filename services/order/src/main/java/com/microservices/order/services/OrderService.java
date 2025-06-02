package com.microservices.order.services;

import com.microservices.order.dto.requests.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Integer createOrder(OrderRequest request);
}
