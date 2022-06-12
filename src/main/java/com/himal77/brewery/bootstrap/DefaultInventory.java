package com.himal77.brewery.bootstrap;

import com.himal77.brewery.domain.Inventory;
import com.himal77.brewery.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultInventory implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    public static final Integer DEFAULT_MAX = 100;
    public static final Integer DEFAULT_MIN = 5;

    @Autowired
    public DefaultInventory(InventoryRepository InventoryRepository) {
        this.inventoryRepository = InventoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Inventory beer_1_inventory = Inventory.builder()
                .beerUpc(DefaultBeer.BEER_1_UPC)
                .quantityOnHand(100)
                .maxOnHand(200)
                .build();

        Inventory beer_2_inventory = Inventory.builder()
                .beerUpc(DefaultBeer.BEER_2_UPC)
                .quantityOnHand(70)
                .maxOnHand(80)
                .build();

        Inventory beer_3_inventory = Inventory.builder()
                .beerUpc(DefaultBeer.BEER_3_UPC)
                .quantityOnHand(30)
                .maxOnHand(35)
                .build();

        inventoryRepository.save(beer_1_inventory);
        inventoryRepository.save(beer_2_inventory);
        inventoryRepository.save(beer_3_inventory);
    }
}
