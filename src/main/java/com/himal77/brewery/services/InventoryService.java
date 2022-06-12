package com.himal77.brewery.services;

import com.himal77.brewery.domain.Beer;
import com.himal77.brewery.domain.Inventory;
import com.himal77.brewery.exception.BeerNotAvailableInInventoryException;
import com.himal77.brewery.exception.BeerNotFoundException;
import com.himal77.brewery.exception.InventoryFullException;
import com.himal77.brewery.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    private final String RESOLVE_BEERDETAILS = "beerdetails";

    private final InventoryRepository inventoryRepository;
    private final BeerService beerService;

    public InventoryService(InventoryRepository inventoryRepository, BeerService beerService) {
        this.inventoryRepository = inventoryRepository;
        this.beerService = beerService;
    }

    public Object findAll(String resolve) {
        if (RESOLVE_BEERDETAILS.equalsIgnoreCase(resolve)) {
            return findAllWithBeerDetails();
        }
        return inventoryRepository.findAll();
    }

    public void addBeer(Inventory inventory) {
        if (!beerService.isBeerAvailable(inventory.getBeerUpc())) {
            throw new BeerNotFoundException();
        }
        inventoryRepository.save(inventory);
    }

    public void addBeer(String beerUpc, Integer quantity) {
        if (!beerService.isBeerAvailable(beerUpc)) {
            throw new BeerNotFoundException();
        }
        Inventory inventory = inventoryRepository.findById(beerUpc)
                .orElse(Inventory.builder()
                        .quantityOnHand(0)
                        .beerUpc(beerUpc)
                        .build());

        if (inventory.getMaxOnHand() < (quantity + inventory.getQuantityOnHand())) {
            throw new InventoryFullException("Inventory will be overloaded with " + quantity + " beer");
        }
        inventory.setQuantityOnHand(inventory.getQuantityOnHand() + quantity);
        inventoryRepository.save(inventory);
    }

    // returning the list of quantity along with beer details.
    public Map<Beer, Integer> findAllWithBeerDetails() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        Map<Beer, Integer> beerInventoryList = new HashMap<>();

        for (Inventory inventory : inventoryList) {
            beerInventoryList.put(
                    beerService.findBeerByUpc(inventory.getBeerUpc()),
                    inventory.getQuantityOnHand());
        }
        return beerInventoryList;
    }

    public boolean isBeerAvailableInInventory(String beerUpc, Integer quantity) {
        if (!beerService.isBeerAvailable(beerUpc)) {
            throw new BeerNotFoundException();
        }
        Inventory inventory = inventoryRepository.findById(beerUpc)
                .orElse(Inventory.builder()
                        .quantityOnHand(0)
                        .beerUpc(beerUpc)
                        .build());
        return inventory.getQuantityOnHand() >= quantity;
    }

    public void reduceBeerQuantityInInventory(String beerUpc, Integer quantity) {
        if (!isBeerAvailableInInventory(beerUpc, quantity)) {
            throw new BeerNotAvailableInInventoryException("Beer No Available in the inventory");
        }

        inventoryRepository.findById(beerUpc).ifPresent(
                inv -> inv.setQuantityOnHand(Math.max((inv.getQuantityOnHand() - quantity), 0)));
        // TODO we can add alert via sms to owner that the inventory is becoming empty.
    }

    public void removeBeer(String beerUpc) {
        if (!isBeerAvailableInInventory(beerUpc, 0)) {
            throw new BeerNotAvailableInInventoryException("Beer No Available in the inventory");
        }

        inventoryRepository.findById(beerUpc)
                .ifPresent(inv -> inv.setQuantityOnHand(0));
    }
}
