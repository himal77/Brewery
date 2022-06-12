package com.himal77.brewery.web;

import com.himal77.brewery.domain.Customer;
import com.himal77.brewery.services.CustomerOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RestController
public class CustomerController {

    private final CustomerOrderService customerService;

    public CustomerController(CustomerOrderService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getCustomer(@PathVariable String customerId) {
        return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
    }
}
