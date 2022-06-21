package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/inventory")
@RestController
public class BeerInventoryController {

    private final BaseUrlConfig baseUrlConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public BeerInventoryController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String resolve) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBeerinventoryurl());
        if (resolve != null) {
            uri = new URI(baseUrlConfig.getBeerinventoryurl() + "?resolve=" + resolve);
        }
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> addBeer(@RequestBody Inventory inventory) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBeerinventoryurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, inventory, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @DeleteMapping("/{beerUpc}")
    public ResponseEntity<Object> removeBeerQuantity(@PathVariable String beerUpc, @RequestParam(required = false) Integer quantity) throws URISyntaxException {
        URI uri;
        if (quantity == 0) {
            uri = new URI(baseUrlConfig.getBeerinventoryurl() + "?beerUpc=" + beerUpc);
        } else {
            uri = new URI(baseUrlConfig.getBeerinventoryurl() + "?beerUpc=" + beerUpc + "&quantity=" + quantity);
        }
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}