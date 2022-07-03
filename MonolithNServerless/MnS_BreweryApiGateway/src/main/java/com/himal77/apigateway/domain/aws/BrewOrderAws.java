package com.himal77.apigateway.domain.aws;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BrewOrderAws {
    private String OrderType = "BREW_ORDER";
    private String breweryId;
    private String beerUpc;
    private Integer quantity;

    public BrewOrderAws(String breweryId, String beerUpc, Integer quantity) {
        this.breweryId = breweryId;
        this.beerUpc = beerUpc;
        this.quantity = quantity;
    }
}
