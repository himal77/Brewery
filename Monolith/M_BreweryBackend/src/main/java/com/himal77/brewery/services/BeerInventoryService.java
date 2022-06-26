package com.himal77.brewery.services;

import com.himal77.brewery.bootstrap.DefaultInventory;
import com.himal77.brewery.domain.BeerInventory;
import com.himal77.brewery.exception.BeerNotAvailableInInventoryException;
import com.himal77.brewery.exception.BeerNotFoundException;
import com.himal77.brewery.exception.InventoryFullException;
import com.himal77.brewery.repositories.BeerInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerInventoryService {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerService beerService;

    public BeerInventoryService(BeerInventoryRepository beerInventoryRepository, BeerService beerService) {
        this.beerInventoryRepository = beerInventoryRepository;
        this.beerService = beerService;
    }

    public List<BeerInventory> findAllInventory() {
        return beerInventoryRepository.findAll();
    }

    public BeerInventory saveBeerInventory(BeerInventory beerInventory) {
        if (!beerService.isBeerAvailable(beerInventory.getBeerUpc())) {
            throw new BeerNotFoundException();
        }
        return beerInventoryRepository.save(beerInventory);
    }

    public BeerInventory saveBeerInventory(String beerUpc, Integer quantity) {
        if (!beerService.isBeerAvailable(beerUpc)) {
            throw new BeerNotFoundException();
        }
        BeerInventory beerInventory = beerInventoryRepository.findById(beerUpc)
                .orElse(BeerInventory.builder()
                        .quantityOnHand(quantity <= DefaultInventory.DEFAULT_MAX ? quantity : DefaultInventory.DEFAULT_MAX)
                        .beerUpc(beerUpc)
                        .maxOnHand(DefaultInventory.DEFAULT_MAX)
                        .minOnHand(DefaultInventory.DEFAULT_MIN)
                        .build());
        return beerInventoryRepository.save(beerInventory);
    }

    public boolean isBeerAvailableInInventory(String beerUpc, Integer quantity) {
        BeerInventory beerInventory = beerInventoryRepository.findById(beerUpc).orElse(null);
        if (!beerService.isBeerAvailable(beerUpc) || beerInventory == null) {
            return false;
        }
        return beerInventory.getQuantityOnHand() > quantity;
    }

    public BeerInventory increaseBeerQuantityInInventory(String beerUpc, Integer quantity) {
        BeerInventory beerInventory = beerInventoryRepository.findById(beerUpc).orElse(null);
        if (beerInventory != null) {
            beerInventory.setQuantityOnHand(beerInventory.getQuantityOnHand() + quantity);
        } else {
            beerInventory = saveBeerInventory(beerUpc, quantity);
        }
        return beerInventoryRepository.save(beerInventory);
    }

    public BeerInventory decreaseBeerQuantityInInventory(String beerUpc, Integer quantity) {
        if (!isBeerAvailableInInventory(beerUpc, quantity)) {
            throw new BeerNotAvailableInInventoryException("Beer Not Available in the inventory");
        }
        BeerInventory beerInventory = beerInventoryRepository.findById(beerUpc).orElse(null);

        if (beerInventory != null) {
            beerInventory.setQuantityOnHand(beerInventory.getQuantityOnHand() - quantity);
        }
        return beerInventoryRepository.save(beerInventory);
    }

    public void removeBeerFromInventory(String beerUpc) {
        if (!isBeerAvailableInInventory(beerUpc, 0)) {
            throw new BeerNotAvailableInInventoryException("Beer Not Available in the inventory");
        }
        beerInventoryRepository.deleteById(beerUpc);
    }
}