package com.himal77.brewery.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Brew {
    @Id
    private String brewId;
    private String breweryId;
    private String beerUpc;
    private Integer quantity;
    private Timestamp brewedDate;
}
