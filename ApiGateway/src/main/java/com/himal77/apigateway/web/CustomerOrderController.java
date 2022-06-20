package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;

@RequestMapping("/customerorders")
@RestController
public class CustomerOrderController {

    private final BaseUrlConfig baseUrlConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerOrderController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<Object> findAllOrder(@RequestParam(required = false) Date date,
                                               @RequestParam(required = false) String customerId) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerorderurl());
        if (date != null) {
            uri = new URI(baseUrlConfig.getCustomerorderurl() + "?date=" + date);
        } else if (customerId != null) {
            uri = new URI(baseUrlConfig.getCustomerorderurl() + "?customerId=" + customerId);
        }
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> placeOrder(@RequestBody CustomerOrder customerOrder) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerorderurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, customerOrder, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
