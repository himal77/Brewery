package com.himal77.brewery.services;

import com.himal77.brewery.domain.BeerOrder;
import com.himal77.brewery.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;

    public OrderService(OrderRepository orderRepository, InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
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

    public void placeOrder(BeerOrder beerOrder) {
        if(!inventoryService.isBeerAvailableInInventory(beerOrder.getBeerUpc(), beerOrder.getQuantity())) {
            return;
        }
        orderRepository.save(beerOrder);
        inventoryService.reduceBeerQuantityInInventory(beerOrder.getBeerUpc(), beerOrder.getQuantity());
    }
}
