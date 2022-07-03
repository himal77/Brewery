package com.himal77.apigateway.domain.aws;

import lombok.*;

@Setter
@Getter
public class CustomerOrderAws {
    private String OrderType = "CUSTOMER_ORDER";
    private String customerId;
    private String beerUpc;
    private String quantity;

    public CustomerOrderAws(String customerId, String beerUpc, String quantity) {
        this.customerId = customerId;
        this.beerUpc = beerUpc;
        this.quantity = quantity;
    }
}
