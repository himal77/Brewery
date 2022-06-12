package com.himal77.brewery.bootstrap;

import com.himal77.brewery.domain.CustomerOrder;
import com.himal77.brewery.repositories.CustomerOrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.UUID;

@Component
public class DefaultCustomerOrder implements CommandLineRunner {

    private final CustomerOrderRepository customerOrderRepository;

    public DefaultCustomerOrder(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public void run(String... args) {
        CustomerOrder order1 = CustomerOrder.builder()
                .customerId("1")
                .beerUpc(DefaultBeer.BEER_1_UPC)
                .orderId(UUID.randomUUID())
                .quantity(10)
                .date(new Date(22, 5, 10))
                .build();

        CustomerOrder order2 = CustomerOrder.builder()
                .customerId("2")
                .beerUpc(DefaultBeer.BEER_2_UPC)
                .orderId(UUID.randomUUID())
                .quantity(10)
                .date(new Date(22, 5, 11))
                .build();

        CustomerOrder order3 = CustomerOrder.builder()
                .customerId("3")
                .beerUpc(DefaultBeer.BEER_3_UPC)
                .orderId(UUID.randomUUID())
                .quantity(5)
                .date(new Date(22, 5, 12))
                .build();

        customerOrderRepository.save(order1);
        customerOrderRepository.save(order2);
        customerOrderRepository.save(order3);
    }
}
