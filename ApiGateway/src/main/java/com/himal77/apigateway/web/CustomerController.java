package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> getAll() throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerurl());
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getCustomer(@PathVariable String customerId) throws URISyntaxException{
        URI uri = new URI(baseUrlConfig.getCustomerurl());
        if (customerId != null) {
            uri = new URI(baseUrlConfig.getCustomerurl() + "?customerId=" + customerId);
        }
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) throws URISyntaxException{
        URI uri = new URI(baseUrlConfig.getCustomerurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, customer, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
