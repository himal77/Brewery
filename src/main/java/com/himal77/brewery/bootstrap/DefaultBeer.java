package com.himal77.brewery.bootstrap;

import com.himal77.brewery.domain.Beer;
import com.himal77.brewery.domain.BeerStyleEnum;
import com.himal77.brewery.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DefaultBeer implements CommandLineRunner {
    private final BeerRepository beerRepository;

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    @Autowired
    public DefaultBeer(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public void run(String... args) {
        Beer mangoBobs = Beer.builder()
                .upc(BEER_1_UPC)
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA)
                .price(BigDecimal.valueOf(12.23))
                .build();

        Beer galaxyCat = Beer.builder()
                .upc(BEER_2_UPC)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(BigDecimal.valueOf(11.13))
                .build();

        Beer pinball = Beer.builder()
                .upc(BEER_3_UPC)
                .beerName("Pinball Porter")
                .beerStyle(BeerStyleEnum.PORTER)
                .price(BigDecimal.valueOf(12.23))
                .build();

        beerRepository.save(galaxyCat);
        beerRepository.save(mangoBobs);
        beerRepository.save(pinball);
    }
}
