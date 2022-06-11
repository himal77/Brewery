package com.himal77.brewery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Inventory extends BaseEntity {

    @Id
    private String beerUpc;
    private Integer quantityInHand;

    @Builder
    public Inventory(UUID id, Timestamp createdDate, Timestamp lastModifiedDate, String beerUpc,
                     Integer quantityOnHand) {
        super(id, createdDate, lastModifiedDate);
        this.beerUpc = beerUpc;
        this.quantityInHand = quantityOnHand;
    }
}
