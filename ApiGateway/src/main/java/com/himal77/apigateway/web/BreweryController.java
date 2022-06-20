package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Brewery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/breweries")
public class BreweryController {

    private final BaseUrlConfig baseUrlConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public BreweryController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<Object> getBrewery(@RequestParam(required = false) String breweryId) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreweryurl());
        if (breweryId != null) {
            uri = new URI(baseUrlConfig.getBreweryurl() + "?breweryId=" + breweryId);
        }
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> addBrewery(@RequestBody Brewery brewery) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreweryurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, brewery, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
