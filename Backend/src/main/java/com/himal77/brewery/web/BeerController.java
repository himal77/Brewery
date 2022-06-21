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
    public ResponseEntity<Object> findAllBeer() {
        return new ResponseEntity<>(beerService.findAllBeer(), HttpStatus.OK);
    }

    @GetMapping("/{beerUpc}")
    public ResponseEntity<Object> findByBeerUpc(@PathVariable String beerUpc) {
        return new ResponseEntity<>(beerService.findByBeerUpc(beerUpc), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveBeer(@RequestBody Beer beer) {
        return new ResponseEntity<>(beerService.saveBeer(beer), HttpStatus.OK);
    }

    @PutMapping("/{beerUpc}")
    public ResponseEntity<Object> updateBeer(@RequestBody Beer beer, @PathVariable String beerUpc) {
        return new ResponseEntity<>(beerService.updateBeer(beer, beerUpc), HttpStatus.OK);
    }

    @DeleteMapping("/{beerUpc}")
    public ResponseEntity<Object> deleteBeer(@PathVariable String beerUpc) {
        beerService.deleteBeer(beerUpc);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
