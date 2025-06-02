package com.microservices.order.data.repositories;

import com.microservices.order.data.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
