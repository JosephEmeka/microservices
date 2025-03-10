package com.microservices.order.services;

import com.microservices.order.dto.requests.OrderRequest;

public interface OrderService {
    Integer createOrder(OrderRequest request);
}
