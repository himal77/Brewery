package com.himal77.brewery.web;

import com.himal77.brewery.domain.BeerOrder;
import com.himal77.brewery.domain.Brewery;
import com.himal77.brewery.domain.Customer;
import com.himal77.brewery.services.BreweryService;
import com.himal77.brewery.services.CustomerService;
import com.himal77.brewery.services.OrderService;
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

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllBrewery() {
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getBrewery(@PathVariable String customerId) {
        return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addBrewery(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
    }
}
