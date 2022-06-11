package com.himal77.brewery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Inventory {
    @Id
    private String beerUpc;
    private Integer quantityOnHand;
    private Integer minOnHand = 5;
    private Integer maxOnHand = 100;
}
