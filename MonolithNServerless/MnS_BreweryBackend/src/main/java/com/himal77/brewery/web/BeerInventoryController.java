package com.himal77.brewery.web;

import com.himal77.brewery.domain.BeerInventory;
import com.himal77.brewery.services.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/beerinventories")
@RestController
public class BeerInventoryController {

    private final BeerInventoryService beerInventoryService;

    @Autowired
    public BeerInventoryController(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @GetMapping
    public ResponseEntity<Object> findAllBeerInventory() {
        return new ResponseEntity<>(beerInventoryService.findAllInventory(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveBeerInventory(@RequestBody BeerInventory beerInventory) {
        return new ResponseEntity<>(beerInventoryService.saveBeerInventory(beerInventory), HttpStatus.OK);
    }

    @PutMapping("/increaseQuantity/{beerUpc}")
    public ResponseEntity<Object> increaseBeerQuantityInInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) {
        return new ResponseEntity<>(beerInventoryService.increaseBeerQuantityInInventory(beerUpc, quantity), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/decreaseQuantity/{beerUpc}")
    public ResponseEntity<Object> decreaseBeerQuantityInInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) {
        return new ResponseEntity<>(beerInventoryService.decreaseBeerQuantityInInventory(beerUpc, quantity), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerUpc}")
    public ResponseEntity<Object> removeBeerFromInventory(@PathVariable String beerUpc) {
        beerInventoryService.removeBeerFromInventory(beerUpc);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}