package com.himal77.brewery.services;

import com.himal77.brewery.domain.Brewery;
import com.himal77.brewery.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreweryService {

    private final BreweryRepository breweryRepository;

    @Autowired
    public BreweryService(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    public List<Brewery> getAllBrewery() {
        return breweryRepository.findAll();
    }

    public Optional<Brewery> getBrewery(String breweryId) {
        return breweryRepository.findById(breweryId);
    }

    public Brewery save(Brewery brewery) {
        return breweryRepository.save(brewery);
    }
}
