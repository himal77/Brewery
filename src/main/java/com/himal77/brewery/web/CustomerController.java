package com.himal77.brewery.web;

import com.himal77.brewery.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    private final OrderService orderService;

    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/myorder")
    public ResponseEntity<Object> getMyOrders(@RequestParam String customerId) {
        return new ResponseEntity<>(orderService.getCustomerSpecificOrder(customerId), HttpStatus.OK);
    }
}
