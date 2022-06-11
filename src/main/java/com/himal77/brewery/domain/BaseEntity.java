package com.himal77.brewery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseEntity {
    private UUID id;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
}
