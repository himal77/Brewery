package com.himal77.apigateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {
    private String customerId;
    private String lastName;
    private String firstName;
    private String address;
}
