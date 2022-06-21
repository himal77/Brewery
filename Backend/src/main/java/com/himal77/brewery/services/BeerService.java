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

    public List<Beer> findAllBeer() {
        return beerRepository.findAll();
    }

    public Beer findByBeerUpc(String upc) {
        return beerRepository.findByBeerUpc(upc);
    }

    public boolean isBeerAvailable(String beerUpc) {
        return findByBeerUpc(beerUpc) != null;
    }

    public Beer saveBeer(Beer beer) {
        return beerRepository.save(beer);
    }

    public Beer updateBeer(Beer beer, String beerUpc) {
        beer.setBeerUpc(beerUpc);
        return beerRepository.save(beer);
    }

    public void deleteBeer(String beerUpc) {
        beerRepository.deleteById(beerUpc);
    }
}