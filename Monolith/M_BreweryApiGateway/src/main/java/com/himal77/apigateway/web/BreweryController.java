package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Brewery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> findAllBrewery() throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreweryurl());
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping("/{breweryId}")
    public ResponseEntity<Object> findBreweryByBreweryId(@PathVariable String breweryId) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreweryurl() + "/" + breweryId);
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> saveBrewery(@RequestBody Brewery brewery) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreweryurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, brewery, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping("/{breweryId}")
    public ResponseEntity<Object> updateBrewery(@RequestBody Brewery brewery, @PathVariable String breweryId) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreweryurl() + "/" + breweryId);
        HttpEntity<Brewery> entity = new HttpEntity<>(brewery);
        ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @DeleteMapping("/{breweryId}")
    public ResponseEntity<Object> deleteBrewery(@PathVariable String breweryId) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreweryurl() + "/" + breweryId);
        restTemplate.delete(uri);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
