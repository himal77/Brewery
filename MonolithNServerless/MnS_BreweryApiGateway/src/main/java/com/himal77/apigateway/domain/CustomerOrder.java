package com.himal77.apigateway.domain;

import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerOrder {
    private UUID orderId;
    private String customerId;
    private String beerUpc;
    private Integer quantity;
    private Date date;
    private Time time;
}
