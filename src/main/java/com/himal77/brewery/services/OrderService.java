package com.himal77.brewery.services;

import com.himal77.brewery.domain.BeerOrder;
import com.himal77.brewery.exception.BeerNotAvailableInInventoryException;
import com.himal77.brewery.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;

    public OrderService(OrderRepository orderRepository, InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
    }

    public List<BeerOrder> findAllOrder() {
        return orderRepository.findAll();
    }

    public List<BeerOrder> findAllOrderOfToday() {
        return orderRepository.findAllByDate(new Date(System.currentTimeMillis()));
    }

    public List<BeerOrder> getCustomerSpecificOrder(String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    public BeerOrder placeOrder(BeerOrder beerOrder) {
        if (!inventoryService.isBeerAvailableInInventory(beerOrder.getBeerUpc(), beerOrder.getQuantity())) {
            throw new BeerNotAvailableInInventoryException("Beer is not available in the inventory");
        }
        inventoryService.reduceBeerQuantityInInventory(beerOrder.getBeerUpc(), beerOrder.getQuantity());
        if (beerOrder.getOrderId() == null) {
            beerOrder.setOrderId(UUID.randomUUID());
        }
        return orderRepository.save(beerOrder);
    }
}