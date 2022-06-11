package com.himal77.brewery.bootstrap;

import com.himal77.brewery.domain.BeerOrder;
import com.himal77.brewery.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.UUID;

@Component
public class DefaultOrder implements CommandLineRunner {

    private final OrderRepository orderRepository;

    public DefaultOrder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) {
        BeerOrder order1 = BeerOrder.builder()
                .customerId("1")
                .beerUpc(DefaultBeer.BEER_1_UPC)
                .orderId(UUID.randomUUID())
                .quantity(10)
                .date(new Date(22, 5, 10))
                .build();

        BeerOrder order2 = BeerOrder.builder()
                .customerId("2")
                .beerUpc(DefaultBeer.BEER_2_UPC)
                .orderId(UUID.randomUUID())
                .quantity(10)
                .date(new Date(22, 5, 11))
                .build();

        BeerOrder order3 = BeerOrder.builder()
                .customerId("3")
                .beerUpc(DefaultBeer.BEER_3_UPC)
                .orderId(UUID.randomUUID())
                .quantity(5)
                .date(new Date(22, 5, 12))
                .build();

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }
}
