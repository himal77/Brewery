package com.himal77.brewery.web;

import com.himal77.brewery.domain.CustomerOrder;
import com.himal77.brewery.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RequestMapping("/customerorders")
@RestController
public class CustomerOrderController {

    private final OrderService orderService;

    public CustomerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Object> findAllOrder(@RequestParam(required = false) Date date, @RequestParam(required = false) String customerId) {
        if(date == null) {
            return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
        } else if(customerId != null) {
            return new ResponseEntity<>(orderService.getCustomerSpecificOrder(customerId), HttpStatus.OK);
        }
        return new ResponseEntity<>(orderService.findOrderOfDate(date), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> placeOrder(@RequestBody CustomerOrder customerOrder) {
        return new ResponseEntity<>(orderService.placeOrder(customerOrder), HttpStatus.OK);
    }
}