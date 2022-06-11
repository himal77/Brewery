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

    @GetMapping("/getall")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(beerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{beerUpc}")
    public ResponseEntity<Object> findByBeerUpc(@PathVariable String beerUpc) {
        return new ResponseEntity<>(beerService.findBeerByUpc(beerUpc), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addBeer(@RequestBody Beer beer) {
        return new ResponseEntity<>(beerService.save(beer), HttpStatus.OK);
    }

    @PutMapping("/update/{beerUpc}")
    public ResponseEntity<Object> updateBeer(@RequestBody Beer beer, @PathVariable String beerUpc) {
        return new ResponseEntity<>(beerService.update(beer, beerUpc), HttpStatus.OK);
    }
}
