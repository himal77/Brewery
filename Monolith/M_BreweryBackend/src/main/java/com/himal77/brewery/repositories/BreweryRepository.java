package com.himal77.brewery.repositories;

import com.himal77.brewery.domain.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, String> {
    public Brewery findBreweryByBreweryId(String breweryId);
}
