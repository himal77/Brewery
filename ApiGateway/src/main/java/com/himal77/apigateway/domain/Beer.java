package com.himal77.apigateway.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer{

    private String upc;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private BigDecimal price;

    @Override
    public String toString() {
        return "{" +
                "upc:'" + upc + '\'' +
                ", beerName:'" + beerName + '\'' +
                ", beerStyle:" + beerStyle +
                ", price:" + price +
                '}';
    }
}