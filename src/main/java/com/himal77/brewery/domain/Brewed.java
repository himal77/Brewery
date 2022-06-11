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
public class Brewed {
    @Id
    private String brewedId;
    private String breweryId;
    private String beerId;
    private Integer quantity;
    private Timestamp brewedDate;
}
