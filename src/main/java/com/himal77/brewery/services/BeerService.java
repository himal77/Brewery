package com.himal77.brewery.services;

import com.himal77.brewery.domain.Beer;
import com.himal77.brewery.repositories.BeerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    private final BeerRepository beerRepository;

    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }
    public Beer findBeerByUpd(String upc) {
        return beerRepository.findByUpc(upc);
    }
}