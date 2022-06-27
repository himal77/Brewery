package com.himal77.apigateway.domain;

import lombok.*;

import java.text.MessageFormat;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BrewOrder {
    private String brewId;
    private String breweryId;
    private String beerUpc;
    private Integer quantity;
    private long brewedDateInMillis;
}
