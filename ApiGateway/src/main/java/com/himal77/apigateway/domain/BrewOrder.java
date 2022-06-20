package com.himal77.apigateway.domain;

import lombok.*;

import java.sql.Timestamp;

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
    private Timestamp brewedDate;
}
