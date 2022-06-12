package com.himal77.brewery.web;

import com.himal77.brewery.domain.CustomerOrder;
import com.himal77.brewery.services.CustomerOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RequestMapping("/customerorders")
@RestController
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @GetMapping
    public ResponseEntity<Object> findAllOrder(@RequestParam(required = false) Date date, @RequestParam(required = false) String customerId) {
        if(date == null) {
            return new ResponseEntity<>(customerOrderService.findAll(), HttpStatus.OK);
        } else if(customerId != null) {
            return new ResponseEntity<>(customerOrderService.getCustomerSpecificOrder(customerId), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerOrderService.findOrderOfDate(date), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> placeOrder(@RequestBody CustomerOrder customerOrder) {
        return new ResponseEntity<>(customerOrderService.placeOrder(customerOrder), HttpStatus.OK);
    }
}
