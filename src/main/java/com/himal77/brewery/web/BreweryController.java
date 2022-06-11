package com.himal77.brewery.web;

import com.himal77.brewery.domain.Brewery;
import com.himal77.brewery.services.BreweryService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brewery")
public class BreweryController {

    private final BreweryService breweryService;

    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllBrewery() {
        return new ResponseEntity<>(breweryService.getAllBrewery(), HttpStatus.OK);
    }

    @GetMapping("/{breweryId}")
    public ResponseEntity<Object> getBrewery(@PathVariable String breweryId) {
       return new ResponseEntity<>(breweryService.getBrewery(breweryId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addBrewery(@RequestBody Brewery brewery) {
        return new ResponseEntity<>(breweryService.save(brewery), HttpStatus.OK);
    }
}
