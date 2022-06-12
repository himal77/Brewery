package com.himal77.brewery.web;

import com.himal77.brewery.domain.Brewery;
import com.himal77.brewery.services.BreweryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/breweries")
public class BreweryController {

    private final BreweryService breweryService;

    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping
    public ResponseEntity<Object> getBrewery(@RequestParam(required = false) String breweryId) {
        if (breweryId != null) {
            return new ResponseEntity<>(breweryService.getBrewery(breweryId), HttpStatus.OK);
        }
        return new ResponseEntity<>(breweryService.getAllBrewery(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addBrewery(@RequestBody Brewery brewery) {
        return new ResponseEntity<>(breweryService.save(brewery), HttpStatus.OK);
    }
}
