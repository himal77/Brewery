package com.himal77.brewery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BeerOrder {
    @Id
    private String orderId;
    private String customerId;
    private String beerUpc;
    private Integer quantity;
}
