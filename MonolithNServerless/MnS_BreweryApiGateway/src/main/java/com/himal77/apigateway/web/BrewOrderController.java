package com.himal77.apigateway.web;

import com.google.gson.Gson;
import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Beer;
import com.himal77.apigateway.domain.BrewOrder;
import com.himal77.apigateway.domain.CustomerOrder;
import com.himal77.apigateway.domain.aws.BrewOrderAws;
import com.himal77.apigateway.domain.aws.CustomerOrderAws;
import com.himal77.apigateway.domain.aws.StepFunctionBody;
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
    private final Gson gson;

    @Autowired
    public BrewOrderController(BaseUrlConfig baseUrlConfig) {
        this.baseUrlConfig = baseUrlConfig;
        this.restTemplate = new RestTemplate();
        gson = new Gson();
    }

    @GetMapping
    public ResponseEntity<Object> findAllBrewOrder() throws URISyntaxException {
        URI uri = new URI(baseUrlConfig.getBreworderurl());
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PostMapping
    public ResponseEntity<Object> saveBrewOrder(@RequestBody BrewOrder brewOrder) throws URISyntaxException {
        String body = getFinalBody(brewOrder);
        URI uri = new URI(baseUrlConfig.getStepfuncurl() + "/placeorder");
        HttpEntity<String> entity = new HttpEntity<>(body);
        ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    private String getFinalBody(BrewOrder brewOrder) {
        BrewOrderAws brewOrderAws = new BrewOrderAws(brewOrder.getBreweryId(), brewOrder.getBeerUpc(), brewOrder.getQuantity());
        StepFunctionBody stepFunctionBody = new StepFunctionBody(gson.toJson(brewOrderAws), baseUrlConfig.getStepfuncarn());
        return gson.toJson(stepFunctionBody);
    }
}
