package com.himal77.brewery.web;

import com.himal77.brewery.domain.BrewOrder;
import com.himal77.brewery.services.BrewOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/breworders")
@RestController
public class BrewOrderController {

    private final BrewOrderService breweryService;

    @Autowired
    public BrewOrderController(BrewOrderService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping
    public ResponseEntity<Object> findAllBrewOrder() {
        return new ResponseEntity<>(breweryService.findAllBrewOrder(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveBrewOrder(@RequestBody BrewOrder brewOrder) {
        return new ResponseEntity<>(breweryService.saveBrewOrder(brewOrder), HttpStatus.OK);
    }
}
