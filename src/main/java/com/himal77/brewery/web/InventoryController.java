package com.himal77.brewery.web;

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

    @GetMapping("/getall")
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String resolve) {
        return new ResponseEntity<>(inventoryService.findAll(resolve), HttpStatus.OK);
    }

    @GetMapping("/addbeer")
    public ResponseEntity<Object> addBeerToInventory(@RequestParam String beerUpc, @RequestParam Integer quantity) {
        inventoryService.addBeer(beerUpc, quantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/removebeer")
    public ResponseEntity<Object> removeBeerFromInventory(@RequestParam String beerUpc) {
        inventoryService.removeBeer(beerUpc);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/removebeerquantity")
    public ResponseEntity<Object> removeBeerQuantityFromInventory(@RequestParam String beerUpc, @RequestParam Integer quantity) {
        inventoryService.reduceBeerQuantityInInventory(beerUpc, quantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
