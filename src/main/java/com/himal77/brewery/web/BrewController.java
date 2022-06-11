package com.himal77.brewery.web;

import com.himal77.brewery.services.BrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/brew")
@RestController
public class BrewController {

    private final BrewService breweryService;

    @Autowired
    public BrewController(BrewService breweryService) {
        this.breweryService = breweryService;
    }

    @PostMapping
    public ResponseEntity<Object> brew(@RequestParam String breweryId, @RequestParam String beerUpc, @RequestParam Integer quantity) {
        breweryService.brew(breweryId, beerUpc, quantity);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<Object> findall() {
        return new ResponseEntity<>(breweryService.findAll(), HttpStatus.OK);
    }
}
