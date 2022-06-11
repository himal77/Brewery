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

    @GetMapping("/getall")
    public ResponseEntity<Object> findAllOrder() {
        return new ResponseEntity<>(orderService.findAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/getall/today")
    public ResponseEntity<Object> findAllOrderFromToday() {
        return new ResponseEntity<>(orderService.findAllOrderOfToday(), HttpStatus.OK);
    }

    @GetMapping("/placeorder")
    public ResponseEntity<Object> placeOrder(@RequestBody BeerOrder beerOrder) {
        return new ResponseEntity<>(orderService.placeOrder(beerOrder), HttpStatus.OK);
    }

    @GetMapping("/getbycustomerid")
    public ResponseEntity<Object> getMyOrders(@RequestParam String customerId) {
        return new ResponseEntity<>(orderService.getCustomerSpecificOrder(customerId), HttpStatus.OK);
    }
}
