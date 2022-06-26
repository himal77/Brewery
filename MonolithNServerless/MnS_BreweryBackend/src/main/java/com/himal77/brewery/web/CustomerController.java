package com.himal77.brewery.web;

import com.himal77.brewery.domain.Customer;
import com.himal77.brewery.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Object> findAllCustomer() {
        return new ResponseEntity<>(customerService.findAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> findCustomerByCustomerId(@PathVariable String customerId) {
        return new ResponseEntity<>(customerService.findAllCustomerByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer, @PathVariable String customerId ) {
        return new ResponseEntity<>(customerService.updateCustomer(customer, customerId), HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Object> saveCustomer( @PathVariable String customerId ) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
