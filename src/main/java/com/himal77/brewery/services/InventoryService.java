package com.himal77.brewery.services;

import com.himal77.brewery.domain.Beer;
import com.himal77.brewery.domain.Inventory;
import com.himal77.brewery.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final BeerService beerService;

    public InventoryService(InventoryRepository inventoryRepository, BeerService beerService) {
        this.inventoryRepository = inventoryRepository;
        this.beerService = beerService;
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    // returning the list of quantity along with beer details.
    public Map<Beer, Integer> findAllWithBeerDetails() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        Map<Beer, Integer> beerInventoryList = new HashMap<>();

        for (Inventory inventory : inventoryList) {
            beerInventoryList.put(
                    beerService.findBeerByUpd(inventory.getBeerUpc()),
                    inventory.getQuantityInHand());
        }
        return beerInventoryList;
    }
}
