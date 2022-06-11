package com.himal77.brewery.services;

import com.himal77.brewery.domain.Brew;
import com.himal77.brewery.exception.BeerNotFoundException;
import com.himal77.brewery.repositories.BrewRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BrewService {

    private final BeerService beerService;
    private final BrewRepository breweryRepository;
    private final InventoryService inventoryService;

    private int brewId = 1;

    public BrewService(BeerService beerService, BrewRepository breweryRepository, InventoryService inventoryService) {
        this.beerService = beerService;
        this.breweryRepository = breweryRepository;
        this.inventoryService = inventoryService;
    }

    public void brew(String breweryId, String beerUpc, Integer quantity) {
        if(!isBeerAvailable(beerUpc)) {
            throw new BeerNotFoundException();
        }

        Brew brewed = Brew.builder()
                .brewId(String.valueOf(brewId++))
                .breweryId(breweryId)
                .brewedDate(new Timestamp(System.currentTimeMillis()))
                .beerUpc(beerUpc)
                .quantity(quantity)
                .build();
        breweryRepository.save(brewed);
        inventoryService.addBeer(beerUpc, quantity);
    }

    private boolean isBeerAvailable(String beerUpc) {
        return beerService.findBeerByUpc(beerUpc) != null;
    }

    public List<Brew> findAll() {
        return breweryRepository.findAll();
    }
}
