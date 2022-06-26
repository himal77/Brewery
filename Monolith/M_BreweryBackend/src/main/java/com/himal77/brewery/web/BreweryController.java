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
    public ResponseEntity<Object> findAllBrewery() {
        return new ResponseEntity<>(breweryService.findAllBrewery(), HttpStatus.OK);
    }

    @GetMapping("/{breweryId}")
    public ResponseEntity<Object> findBreweryByBreweryId(@PathVariable String breweryId) {
        return new ResponseEntity<>(breweryService.findBreweryByBreweryId(breweryId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveBrewery(@RequestBody Brewery brewery) {
        return new ResponseEntity<>(breweryService.saveBrewery(brewery), HttpStatus.OK);
    }

    @PutMapping("/{breweryId}")
    public ResponseEntity<Object> updateBrewery(@RequestBody Brewery brewery, @PathVariable String breweryId) {
        return new ResponseEntity<>(breweryService.updateBrewery(brewery, breweryId), HttpStatus.OK);
    }

    @DeleteMapping("/{breweryId}")
    public ResponseEntity<Object> deleteBrewery(@PathVariable String breweryId) {
        breweryService.deleteBrewery(breweryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
