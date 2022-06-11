package com.himal77.brewery.web;

import com.himal77.brewery.services.BeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/beers")
@Controller
public class BeerController {

    private BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/findall")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(beerService.findAll(), HttpStatus.OK);
    }
}
