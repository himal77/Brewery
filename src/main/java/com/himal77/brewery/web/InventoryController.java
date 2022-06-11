package com.himal77.brewery.web;

import com.himal77.brewery.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/inventory")
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/findall")
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String resolve) {
        if(resolve != null && resolve.equalsIgnoreCase("beerdetails")) {
//            String inventoryDetailInJson =new ObjectMapper().writeValueAsString(inventoryService.findAllWithBeerDetails());
            return new ResponseEntity<>(inventoryService.findAllWithBeerDetails(), HttpStatus.OK);
        }
        return new ResponseEntity<>(inventoryService.findAll(), HttpStatus.OK);
    }
}
