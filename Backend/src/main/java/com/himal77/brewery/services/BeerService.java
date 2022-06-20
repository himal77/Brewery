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
    public Beer findBeerByUpc(String upc) {
        return beerRepository.findByUpc(upc);
    }

    public boolean isBeerAvailable(String beerUpc) {
        return findBeerByUpc(beerUpc) != null;
    }

    public Beer save(Beer beer) {
        return beerRepository.save(beer);
    }

    public Beer update(Beer beer, String beerUpc) {
        Beer repoBeer = beerRepository.findByUpc(beerUpc);
        repoBeer.setBeerName(beer.getBeerName());
        repoBeer.setPrice(beer.getPrice());
        repoBeer.setBeerStyle(beer.getBeerStyle());
        return beerRepository.save(repoBeer);
    }
}