package com.himal77.brewery.web;

import com.himal77.brewery.domain.BeerOrder;
import com.himal77.brewery.domain.Customer;
import com.himal77.brewery.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
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

    @GetMapping("/placeorder")
    public ResponseEntity<Object> placeOrder(@RequestBody BeerOrder beerOrder) {
        return new ResponseEntity<>(orderService.placeOrder(beerOrder), HttpStatus.OK);
    }
}
