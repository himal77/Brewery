package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Brewery;
import com.himal77.apigateway.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/customers")
@RestController
public class CustomerController {

    private final BaseUrlConfig baseUrlConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<Object> findAllCustomer() throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerurl());
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> findCustomerByCustomerId(@PathVariable String customerId) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerurl() + "/" + customerId);
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, customer, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer, @PathVariable String customerId ) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerurl() + "/" + customerId);
        HttpEntity<Customer> entity = new HttpEntity<>(customer);
        ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Object> saveCustomer( @PathVariable String customerId ) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerurl() + "/" + customerId);
        restTemplate.delete(uri);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
