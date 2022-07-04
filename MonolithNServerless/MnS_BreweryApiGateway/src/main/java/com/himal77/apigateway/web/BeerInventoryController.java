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
        client = WebClient.create(baseUrlConfig.getServerlessurl());
    }

    @GetMapping
    public ResponseEntity<Object> findAllBeerInventory(@RequestParam(required = false) String beerUpc) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrlConfig.getServerlessurl() + lambdaBeerInventoriesUrl;
        if (beerUpc != null) {
            url += "?beerUpc=" + beerUpc;
        }
        HttpEntity<String> entity = new HttpEntity<>("");
        ResponseEntity<Object> response = restTemplate.exchange(new URI(url), HttpMethod.GET, entity, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> saveBeerInventory(@RequestBody BeerInventory beerInventory) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(baseUrlConfig.getBeerinventoryurl());
        ResponseEntity<Object> response = restTemplate.postForEntity(uri, beerInventory, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping("/increaseQuantity/{beerUpc}")
    public Mono<Object> increaseBeerQuantityInInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) throws URISyntaxException {
        String url = baseUrlConfig.getServerlessurl() + lambdaChangeBeerQuantityUrl;
        if (beerUpc != null) {
            url += MessageFormat.format("?beerUpc={0}&changeCmdType=increase&quantity={1}", beerUpc, quantity);
        }
        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Object.class);
    }

    @PutMapping("/decreaseQuantity/{beerUpc}")
    public Mono<Object> decreaseBeerQuantityInInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) throws URISyntaxException {
        String url = baseUrlConfig.getServerlessurl() + lambdaChangeBeerQuantityUrl;
        if (beerUpc != null) {
            url += MessageFormat.format("?beerUpc={0}&changeCmdType=decrease&quantity={1}", beerUpc, quantity);
        }
        return client.get()
                .uri(url)
                .retrieve().bodyToMono(Object.class);
    }

    @DeleteMapping("/{beerUpc}")
    public ResponseEntity<Object> removeBeerFromInventory(@PathVariable String beerUpc) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(baseUrlConfig.getBeerinventoryurl() + "/" + beerUpc);
        restTemplate.delete(uri);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}