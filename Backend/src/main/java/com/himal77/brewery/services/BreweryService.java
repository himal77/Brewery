package com.himal77.brewery.services;

import com.himal77.brewery.domain.Beer;
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

    public List<Brewery> findAllBrewery() {
        return breweryRepository.findAll();
    }

    public Optional<Brewery> findBreweryByBreweryId(String breweryId) {
        return breweryRepository.findById(breweryId);
    }

    public Brewery saveBrewery(Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    public Brewery updateBrewery(Brewery brewery, String breweryId) {
        brewery.setBreweryId(breweryId);
        return breweryRepository.save(brewery);
    }

    public void deleteBrewery(String breweryId) {
        breweryRepository.deleteById(breweryId);
    }
}
