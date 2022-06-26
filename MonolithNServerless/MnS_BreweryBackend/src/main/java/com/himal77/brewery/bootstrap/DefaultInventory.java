package com.himal77.brewery.bootstrap;

import com.himal77.brewery.domain.BeerInventory;
import com.himal77.brewery.repositories.BeerInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultInventory implements CommandLineRunner {

    private final BeerInventoryRepository beerInventoryRepository;

    public static final Integer DEFAULT_MAX = 100;
    public static final Integer DEFAULT_MIN = 5;

    @Autowired
    public DefaultInventory(BeerInventoryRepository BeerInventoryRepository) {
        this.beerInventoryRepository = BeerInventoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        BeerInventory beer_1_Beer_inventory = BeerInventory.builder()
                .beerUpc(DefaultBeer.BEER_1_UPC)
                .quantityOnHand(100)
                .maxOnHand(200)
                .build();

        BeerInventory beer_2_Beer_inventory = BeerInventory.builder()
                .beerUpc(DefaultBeer.BEER_2_UPC)
                .quantityOnHand(70)
                .maxOnHand(80)
                .build();

        BeerInventory beer_3_Beer_inventory = BeerInventory.builder()
                .beerUpc(DefaultBeer.BEER_3_UPC)
                .quantityOnHand(30)
                .maxOnHand(35)
                .build();

        beerInventoryRepository.save(beer_1_Beer_inventory);
        beerInventoryRepository.save(beer_2_Beer_inventory);
        beerInventoryRepository.save(beer_3_Beer_inventory);
    }
}
