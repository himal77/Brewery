package com.himal77.apigateway.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Customer;
import com.himal77.apigateway.domain.CustomerOrder;
import com.himal77.apigateway.domain.aws.CustomerOrderAws;
import com.himal77.apigateway.domain.aws.StepFunctionBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.MessageFormat;

@RequestMapping("/customerorders")
@RestController
public class CustomerOrderController {

    private final BaseUrlConfig baseUrlConfig;
    private final RestTemplate restTemplate;
    private final Gson gson;
    WebClient client = WebClient.create();

    @Autowired
    public CustomerOrderController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
        gson = new Gson();
    }

    @GetMapping
    public ResponseEntity<Object> findAllCustomerOrder(@RequestParam(required = false) Date date) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerorderurl());
        if (date != null) {
            uri = new URI(baseUrlConfig.getCustomerorderurl() + "?date=" + date);
        }
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> findAllCustomerOrderByCustomerId(@PathVariable String customerId) throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getCustomerorderurl());
        if (customerId != null) {
            uri = new URI(baseUrlConfig.getCustomerorderurl() + "/" + customerId);
        }
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public Flux<Object> placeOrder(@RequestBody CustomerOrder customerOrder) throws URISyntaxException {
        String body = getFinalBody(customerOrder);
        WebClient.ResponseSpec responseSpec = client.post()
                .uri(baseUrlConfig.getStepfuncurl() + "/placeorder")
                .body(Mono.just(body), String.class)
                .retrieve();
        return responseSpec.bodyToFlux(Object.class);
    }

    private String getFinalBody(CustomerOrder customerOrder) {
        CustomerOrderAws customerOrderAws = new CustomerOrderAws(customerOrder.getCustomerId(), customerOrder.getBeerUpc(), customerOrder.getQuantity().toString());
        StepFunctionBody stepFunctionBody = new StepFunctionBody(gson.toJson(customerOrderAws), baseUrlConfig.getStepfuncarn());
        return gson.toJson(stepFunctionBody);
    }
}
