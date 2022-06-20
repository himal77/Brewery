package com.himal77.apigateway.domain;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Inventory {

    private String beerUpc;
    private Integer quantityOnHand;
    private Integer minOnHand = 5;
    private Integer maxOnHand = 100;
}
