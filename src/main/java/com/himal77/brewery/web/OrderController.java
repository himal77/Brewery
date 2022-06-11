package com.himal77.brewery.web;

import com.himal77.brewery.domain.BeerOrder;
import com.himal77.brewery.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/findall")
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String resolve) {
        return new ResponseEntity<>(orderService.findAll(resolve), HttpStatus.OK);
    }

    @GetMapping("/placeorder")
    public ResponseEntity<Object> placeOrder(@RequestBody BeerOrder beerOrder) {
        return new ResponseEntity<>(orderService.placeOrder(beerOrder), HttpStatus.OK);
    }
}
