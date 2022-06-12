package com.himal77.brewery.services;

import com.himal77.brewery.domain.CustomerOrder;
import com.himal77.brewery.exception.BeerNotAvailableInInventoryException;
import com.himal77.brewery.repositories.CustomerOrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final InventoryService inventoryService;

    public OrderService(CustomerOrderRepository customerOrderRepository, InventoryService inventoryService) {
        this.customerOrderRepository = customerOrderRepository;
        this.inventoryService = inventoryService;
    }

    public List<CustomerOrder> findAll() {
        return customerOrderRepository.findAll();
    }

    public List<CustomerOrder> findOrderOfDate(Date date) {
        return customerOrderRepository.findAllByDate(date);
    }

    public List<CustomerOrder> getCustomerSpecificOrder(String customerId) {
        return customerOrderRepository.findAllByCustomerId(customerId);
    }

    public CustomerOrder placeOrder(CustomerOrder customerOrder) {
        if (!inventoryService.isBeerAvailableInInventory(customerOrder.getBeerUpc(), customerOrder.getQuantity())) {
            throw new BeerNotAvailableInInventoryException("Beer is not available in the inventory");
        }
        inventoryService.reduceBeerQuantityInInventory(customerOrder.getBeerUpc(), customerOrder.getQuantity());
        if (customerOrder.getOrderId() == null) {
            customerOrder.setOrderId(UUID.randomUUID());
        }
        return customerOrderRepository.save(customerOrder);
    }
}