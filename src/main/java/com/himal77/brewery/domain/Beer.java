package com.himal77.brewery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer{

    @Id
    private String upc;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private BigDecimal price;
    private Integer minOnHand = 10;
    private Integer maxOnHand;

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