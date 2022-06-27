package com.himal77.brewery.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimelyCustomerOrder implements CommandLineRunner {
    List<String> beerUpc;

    public TimelyCustomerOrder(){
        beerUpc = new ArrayList<String>();
    }

    @Override
    public void run(String... args) throws Exception {
        beerUpc.add(DefaultBeer.BEER_1_UPC);
        beerUpc.add(DefaultBeer.BEER_2_UPC);
        beerUpc.add(DefaultBeer.BEER_3_UPC);
    }
}
