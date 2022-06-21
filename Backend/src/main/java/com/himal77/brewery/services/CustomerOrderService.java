package com.himal77.brewery.services;

import com.himal77.brewery.domain.CustomerOrder;
import com.himal77.brewery.exception.BeerNotAvailableInInventoryException;
import com.himal77.brewery.repositories.CustomerOrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final BeerInventoryService beerInventoryService;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, BeerInventoryService beerInventoryService) {
        this.customerOrderRepository = customerOrderRepository;
        this.beerInventoryService = beerInventoryService;
    }

    public List<CustomerOrder> findAllCustomerOrder() {
        return customerOrderRepository.findAll();
    }

    public List<CustomerOrder> findCustomerOrderByDate(Date date) {
        return customerOrderRepository.findAllByDate(date);
    }

    public List<CustomerOrder> findAllCustomerOrderByCustomerId(String customerId) {
        return customerOrderRepository.findAllByCustomerId(customerId);
    }

    public CustomerOrder placeOrder(CustomerOrder customerOrder) {
        if (!beerInventoryService.isBeerAvailableInInventory(customerOrder.getBeerUpc(), customerOrder.getQuantity())) {
            throw new BeerNotAvailableInInventoryException("Beer is not available in the inventory");
        }
        beerInventoryService.decreaseBeerQuantityInInventory(customerOrder.getBeerUpc(), customerOrder.getQuantity());
        if (customerOrder.getOrderId() == null) {
            customerOrder.setOrderId(UUID.randomUUID());
        }
        return customerOrderRepository.save(customerOrder);
    }
}