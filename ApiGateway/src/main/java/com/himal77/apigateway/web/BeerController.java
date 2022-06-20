package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Beer;
import com.himal77.apigateway.domain.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/beers")
@RestController
public class BeerController {

    private final BaseUrlConfig baseUrlConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public BeerController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String beerUpc) throws URISyntaxException {

        URI uri = new URI(baseUrlConfig.getBeerurl());
        if (beerUpc != null) {
            uri = new URI(baseUrlConfig.getBeerurl() + "?beerUpc=" + beerUpc);
        }
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> addBeer(@RequestBody Beer beer) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBeerurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, beer, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping("/{beerUpc}")
    public ResponseEntity<Object> updateBeer(@RequestBody Beer beer, @PathVariable String beerUpc) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBeerurl() + "/" + beerUpc);
        HttpEntity<Beer> entity = new HttpEntity<>(beer);
        ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
