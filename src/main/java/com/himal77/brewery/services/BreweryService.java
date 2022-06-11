package com.himal77.brewery.services;

import com.himal77.brewery.domain.Brewed;
import com.himal77.brewery.repositories.BreweryRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BreweryService {

    private final BeerService beerService;
    private final BreweryRepository breweryRepository;
    private final InventoryService inventoryService;

    private int brewId = 1;

    public BreweryService(BeerService beerService, BreweryRepository breweryRepository, InventoryService inventoryService) {
        this.beerService = beerService;
        this.breweryRepository = breweryRepository;
        this.inventoryService = inventoryService;
    }

    public void brew(String beerUpc, Integer quantity) throws Exception {
        if(!isBeerAvailable(beerUpc)) {
            throw new Exception("Beer Upc not found");
        }

        Brewed brewed = Brewed.builder()
                .brewedId(String.valueOf(brewId++))
                .breweryId("1")
                .brewedDate(new Timestamp(System.currentTimeMillis()))
                .beerId(beerUpc)
                .quantity(quantity)
                .build();
        breweryRepository.save(brewed);
    }

    private boolean isBeerAvailable(String beerUpc) {
        return beerService.findBeerByUpd(beerUpc) != null;
    }

    public List<Brewed> findAll() {
        return breweryRepository.findAll();
    }
}
