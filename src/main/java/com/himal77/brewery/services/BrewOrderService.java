package com.himal77.brewery.services;

import com.himal77.brewery.domain.BrewOrder;
import com.himal77.brewery.exception.BeerNotFoundException;
import com.himal77.brewery.repositories.BrewOrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BrewOrderService {

    private final BeerService beerService;
    private final BrewOrderRepository breweryRepository;
    private final InventoryService inventoryService;

    public BrewOrderService(BeerService beerService, BrewOrderRepository breweryRepository, InventoryService inventoryService) {
        this.beerService = beerService;
        this.breweryRepository = breweryRepository;
        this.inventoryService = inventoryService;
    }

    public void brew(BrewOrder brewOrder) {
        if(!isBeerAvailable(brewOrder.getBeerUpc())) {
            throw new BeerNotFoundException();
        }

        BrewOrder brewed = BrewOrder.builder()
                .brewId(brewOrder.getBrewId())
                .breweryId(brewOrder.getBreweryId())
                .brewedDate(new Timestamp(System.currentTimeMillis()))
                .beerUpc(brewOrder.getBeerUpc())
                .quantity(brewOrder.getQuantity())
                .build();
        breweryRepository.save(brewed);
        inventoryService.addBeer(brewOrder.getBeerUpc(), brewOrder.getQuantity());
    }

    private boolean isBeerAvailable(String beerUpc) {
        return beerService.findBeerByUpc(beerUpc) != null;
    }

    public List<BrewOrder> findAll() {
        return breweryRepository.findAll();
    }
}
