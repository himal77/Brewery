package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.BeerInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

@RequestMapping("/beerinventories")
@RestController
public class BeerInventoryController {

    private final BaseUrlConfig baseUrlConfig;
    WebClient client = WebClient.create();

    private final String lambdaBeerInventoriesUrl = "/beerinventories";
    private final String lambdaChangeBeerQuantityUrl = "/beerinventories/changequantity";

    @Autowired
    public BeerInventoryController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
    }

    @GetMapping
    public Flux<Object> findAllBeerInventory(@RequestParam(required = false) String beerUpc) throws URISyntaxException {
        String url = baseUrlConfig.getServerlessurl() + lambdaBeerInventoriesUrl;
        if (beerUpc != null) {
            url += "?beerUpc=" + beerUpc;
        }
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(url)
                .retrieve();
        return responseSpec.bodyToFlux(Object.class);
    }

    @PostMapping
    public ResponseEntity<Object> saveBeerInventory(@RequestBody BeerInventory beerInventory) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(baseUrlConfig.getBeerinventoryurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, beerInventory, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping("/increaseQuantity/{beerUpc}")
    public Flux<Object> increaseBeerQuantityInInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) throws URISyntaxException {
        String url = baseUrlConfig.getServerlessurl() + lambdaChangeBeerQuantityUrl;
        if (beerUpc != null) {
            url += MessageFormat.format("?beerUpc={0}&changeCmdType=increase&quantity={1}", beerUpc, quantity);
        }
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(url)
                .retrieve();
        return responseSpec.bodyToFlux(Object.class);
    }

    @PutMapping("/decreaseQuantity/{beerUpc}")
    public Flux<Object> decreaseBeerQuantityInInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) throws URISyntaxException {
        String url = baseUrlConfig.getServerlessurl() + lambdaChangeBeerQuantityUrl;
        if (beerUpc != null) {
            url += MessageFormat.format("?beerUpc={0}&changeCmdType=decrease&quantity={1}", beerUpc, quantity);
        }
        WebClient.ResponseSpec responseSpec = client.get()
                .uri(url)
                .retrieve();
        return responseSpec.bodyToFlux(Object.class);
    }

    @DeleteMapping("/{beerUpc}")
    public ResponseEntity<Object> removeBeerFromInventory(@PathVariable String beerUpc) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(baseUrlConfig.getBeerinventoryurl() + "/" + beerUpc);
        restTemplate.delete(uri);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}