package com.himal77.apigateway.web;

import com.himal77.apigateway.config.BaseUrlConfig;
import com.himal77.apigateway.domain.Beer;
import com.himal77.apigateway.domain.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequestMapping("/beers")
@RestController
public class BeerController {

    private final BaseUrlConfig baseUrlConfig;

    @Autowired
    public BeerController(BaseUrlConfig baseUrlConfig) {
     this.baseUrlConfig = baseUrlConfig;
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String beerUpc) {
        Beer beer = Beer.builder()
                .upc(baseUrlConfig.getBeerurl())
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA)
                .price(BigDecimal.valueOf(12.23))
                .build();

        return new ResponseEntity<Object>(beer, HttpStatus.OK);
    }
}
