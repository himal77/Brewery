package com.himal77.brewery.web;

import com.himal77.brewery.services.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/brewery")
@RestController
public class BreweryController {

    private final BreweryService breweryService;

    @Autowired
    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping("/brew")
    public ResponseEntity brew(@RequestParam String beerUpc, @RequestParam Integer quantity) throws Exception {
         breweryService.brew(beerUpc, quantity);
         return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<Object> findall() {
        return new ResponseEntity<>(breweryService.findAll(), HttpStatus.OK);
    }
}
