package com.himal77.brewery.services;

import com.himal77.brewery.domain.Beer;
import com.himal77.brewery.domain.BeerOrder;
import com.himal77.brewery.repositories.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<BeerOrder> findAll(String resolve) {
        if(resolve == null) {
            return orderRepository.findAll();
        }
        return null;
    }

    public List<BeerOrder> getCustomerSpecificOrder(String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }
}
