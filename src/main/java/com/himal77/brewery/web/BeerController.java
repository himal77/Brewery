package com.himal77.brewery.web;

import com.himal77.brewery.domain.Beer;
import com.himal77.brewery.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/beers")
@RestController
public class BeerController {

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String beerUpc) {
        if (beerUpc != null) {
            return new ResponseEntity<>(beerService.findBeerByUpc(beerUpc), HttpStatus.OK);
        }
        return new ResponseEntity<>(beerService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addBeer(@RequestBody Beer beer) {
        return new ResponseEntity<>(beerService.save(beer), HttpStatus.OK);
    }

    @PutMapping("/{beerUpc}")
    public ResponseEntity<Object> updateBeer(@RequestBody Beer beer, @PathVariable String beerUpc) {
        return new ResponseEntity<>(beerService.update(beer, beerUpc), HttpStatus.OK);
    }
}
