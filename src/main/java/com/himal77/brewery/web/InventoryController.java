package com.himal77.brewery.web;

import com.himal77.brewery.domain.Inventory;
import com.himal77.brewery.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/inventory")
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String resolve) {
        return new ResponseEntity<>(inventoryService.findAll(resolve), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addBeerToInventory(@RequestBody Inventory inventory) {
        inventoryService.addBeer(inventory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerUpc}")
    public ResponseEntity<Object> removeBeerQuantityFromInventory(@PathVariable String beerUpc, @RequestParam Integer quantity) {
        if (quantity == 0) {
            inventoryService.removeBeer(beerUpc);
        } else {
            inventoryService.reduceBeerQuantityInInventory(beerUpc, quantity);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}