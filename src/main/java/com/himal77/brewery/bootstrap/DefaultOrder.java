package com.himal77.brewery.bootstrap;

import com.himal77.brewery.domain.BeerOrder;
import com.himal77.brewery.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
                .beerId(DefaultBeer.BEER_1_UPC)
                .orderId("1")
                .quantity(100)
                .build();

        BeerOrder order2 = BeerOrder.builder()
                .customerId("2")
                .beerId(DefaultBeer.BEER_2_UPC)
                .orderId("1")
                .quantity(100)
                .build();

        BeerOrder order3 = BeerOrder.builder()
                .customerId("2")
                .beerId(DefaultBeer.BEER_3_UPC)
                .orderId("1")
                .quantity(100)
                .build();

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }
}
