package com.himal77.brewery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BeerOrder {
    @Id
    private UUID orderId;
    private String customerId;
    private String beerUpc;
    private Integer quantity;
    private Date date;
    private Time time;
}
