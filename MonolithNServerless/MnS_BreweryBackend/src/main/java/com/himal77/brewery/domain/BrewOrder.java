package com.himal77.brewery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BrewOrder {
    @Id
    private String brewId;
    private String breweryId;
    private String beerUpc;
    private Integer quantity;
    private Date date;
    private Time time;
}
