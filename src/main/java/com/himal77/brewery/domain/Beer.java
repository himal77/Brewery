package com.himal77.brewery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Beer extends BaseEntity{

    @Id
    private String upc;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private BigDecimal price;

    @Builder
    public Beer(UUID id, Timestamp createdDate, Timestamp lastModifiedDate, String beerName,
                BeerStyleEnum beerStyle, String upc, BigDecimal price) {
        super(id, createdDate, lastModifiedDate);
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.upc = upc;
        this.price = price;
    }

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