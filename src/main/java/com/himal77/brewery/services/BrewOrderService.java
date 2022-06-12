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
    private final BrewOrderRepository brewOrderRepository;
    private final InventoryService inventoryService;

    public BrewOrderService(BeerService beerService, BrewOrderRepository brewOrderRepository, InventoryService inventoryService) {
        this.beerService = beerService;
        this.brewOrderRepository = brewOrderRepository;
        this.inventoryService = inventoryService;
    }

    public void brew(BrewOrder brewOrder) {
        if(!beerService.isBeerAvailable(brewOrder.getBeerUpc())) {
            throw new BeerNotFoundException();
        }

        BrewOrder brewed = BrewOrder.builder()
                .brewId(brewOrder.getBrewId())
                .breweryId(brewOrder.getBreweryId())
                .brewedDate(new Timestamp(System.currentTimeMillis()))
                .beerUpc(brewOrder.getBeerUpc())
                .quantity(brewOrder.getQuantity())
                .build();
        brewOrderRepository.save(brewed);
        inventoryService.addBeer(brewOrder.getBeerUpc(), brewOrder.getQuantity());
    }

    public List<BrewOrder> findAll() {
        return brewOrderRepository.findAll();
    }
}
