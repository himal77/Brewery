package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.BeerInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

@RequestMapping("/beerinventories")
@RestController
public class BeerInventoryController {

    private final BaseUrlConfig baseUrlConfig;
    private final RestTemplate restTemplate;

    private final String lambdaBeerInventoriesUrl = "/beerinventories";
    private final String lambdaChangeBeerQuantityUrl = "/beerinventories/changequantity";

    @Autowired
    public BeerInventoryController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<Object> findAllBeerInventory(@RequestParam(required = false) String beerUpc) throws URISyntaxException {
        String url = baseUrlConfig.getServerlessurl() + lambdaBeerInventoriesUrl;
        if (beerUpc != null) {
            url += "?beerUpc=" + beerUpc;
        }
        System.out.print(url);
        ResponseEntity<Object> response = restTemplate.getForEntity(new URI(url), Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> saveBeerInventory(@RequestBody BeerInventory beerInventory) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBeerinventoryurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, beerInventory, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping("/increaseQuantity/{beerUpc}")
    public ResponseEntity<Object> increaseBeerQuantityInInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) throws URISyntaxException {
        String url = baseUrlConfig.getServerlessurl() + lambdaChangeBeerQuantityUrl;
        if (beerUpc != null) {
            url += MessageFormat.format("?beerUpc={0}&changeCmdType=increase&quantity={1}", beerUpc, quantity);
        }
        System.out.println(url);
        ResponseEntity<Object> response = restTemplate.getForEntity(new URI(url), Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping("/decreaseQuantity/{beerUpc}")
    public ResponseEntity<Object> decreaseBeerQuantityInInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) throws URISyntaxException {
        String url = baseUrlConfig.getServerlessurl() + lambdaChangeBeerQuantityUrl;
        if (beerUpc != null) {
            url += MessageFormat.format("?beerUpc={0}&changeCmdType=decrease&quantity={1}", beerUpc, quantity);
        }
        System.out.print(url);
        ResponseEntity<Object> response = restTemplate.getForEntity(new URI(url), Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @DeleteMapping("/{beerUpc}")
    public ResponseEntity<Object> removeBeerFromInventory(@PathVariable String beerUpc) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBeerinventoryurl() + "/" + beerUpc);
        restTemplate.delete(uri);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}