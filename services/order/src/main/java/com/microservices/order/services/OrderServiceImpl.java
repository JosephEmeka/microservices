package com.microservices.order.services;

import com.microservices.order.data.models.customer.CustomerClient;
import com.microservices.order.services.products.ProductClient;
import com.microservices.order.data.repositories.OrderRepository;
import com.microservices.order.dto.requests.OrderRequest;
import com.microservices.order.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;

    @Override
    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException("Cannot create Order:: No customer exists with the provided ID"));
        this.productClient.purchaseProducts(request.products());
        var order = this.repository.findById(request.orderId());
        return null;
    }
}
