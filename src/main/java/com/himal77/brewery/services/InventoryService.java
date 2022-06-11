package com.himal77.brewery.services;

import com.himal77.brewery.domain.Beer;
import com.himal77.brewery.domain.Inventory;
import com.himal77.brewery.exception.BeerNotFoundException;
import com.himal77.brewery.exception.InventoryFullException;
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

    public void save(String beerUpc, Integer quantity) {
        if(!isBeerAvailable(beerUpc)){
            throw new BeerNotFoundException();
        }
        if(isInventoryOverloaded(beerUpc, quantity)) {
            throw new InventoryFullException("Inventory will be overloaded with " + quantity + " beer");
        }

        Inventory inventory = inventoryRepository.findById(beerUpc).orElse(null);
        if(inventory == null) {
            inventory = Inventory.builder().beerUpc(beerUpc).quantityOnHand(quantity).build();
        } else {
            inventory.setQuantityOnHand(inventory.getQuantityOnHand() + quantity);
        }

        inventoryRepository.save(inventory);
    }

    // returning the list of quantity along with beer details.
    public Map<Beer, Integer> findAllWithBeerDetails() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        Map<Beer, Integer> beerInventoryList = new HashMap<>();

        for (Inventory inventory : inventoryList) {
            beerInventoryList.put(
                    beerService.findBeerByUpd(inventory.getBeerUpc()),
                    inventory.getQuantityOnHand());
        }
        return beerInventoryList;
    }

    private boolean isBeerAvailable(String beerUpc) {
        return beerService.findBeerByUpd(beerUpc) != null;
    }

    private boolean isInventoryOverloaded(String beerUpc, Integer quantity) {
        Beer beer = beerService.findBeerByUpd(beerUpc);
        Inventory inventory = inventoryRepository.findById(beerUpc).orElse(null);

        if(inventory != null) {
            return (inventory.getQuantityOnHand() + quantity) <= beer.getMaxOnHand();
        } else {
            return quantity <= beer.getMaxOnHand();
        }
    }
}
