package com.himal77.brewery.services;

import com.himal77.brewery.domain.BrewOrder;
import com.himal77.brewery.exception.BeerNotFoundException;
import com.himal77.brewery.repositories.BrewOrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class BrewOrderService {

    private final BeerService beerService;
    private final BrewOrderRepository brewOrderRepository;
    private final BeerInventoryService beerInventoryService;

    public BrewOrderService(BeerService beerService, BrewOrderRepository brewOrderRepository, BeerInventoryService beerInventoryService) {
        this.beerService = beerService;
        this.brewOrderRepository = brewOrderRepository;
        this.beerInventoryService = beerInventoryService;
    }

    public BrewOrder saveBrewOrder(BrewOrder brewOrder) {
        if(!beerService.isBeerAvailable(brewOrder.getBeerUpc())) {
            throw new BeerNotFoundException();
        }

        BrewOrder brewed = BrewOrder.builder()
                .brewId(brewOrder.getBrewId())
                .breweryId(brewOrder.getBreweryId())
                .date(new Date(System.currentTimeMillis()))
                .time(new Time(System.currentTimeMillis()))
                .beerUpc(brewOrder.getBeerUpc())
                .quantity(brewOrder.getQuantity())
                .build();
        BrewOrder savedBrewOrder = brewOrderRepository.save(brewed);
        beerInventoryService.saveBeerInventory(brewOrder.getBeerUpc(), brewOrder.getQuantity());
        return savedBrewOrder;
    }

    public List<BrewOrder> findAllBrewOrder() {
        return brewOrderRepository.findAll();
    }
}
