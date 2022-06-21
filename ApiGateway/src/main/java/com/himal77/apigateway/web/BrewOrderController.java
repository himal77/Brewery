package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Beer;
import com.himal77.apigateway.domain.BrewOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/breworders")
@RestController
public class BrewOrderController {

    private final BaseUrlConfig baseUrlConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public BrewOrderController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<Object> findAllBrewOrder() throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreworderurl());
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> saveBrewOrder(@RequestBody BrewOrder brewOrder) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreworderurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, brewOrder, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
