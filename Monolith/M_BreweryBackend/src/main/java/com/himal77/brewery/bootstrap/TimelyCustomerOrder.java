package com.himal77.brewery.bootstrap;

import com.himal77.brewery.domain.CustomerOrder;
import com.himal77.brewery.services.CustomerOrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TimelyCustomerOrder implements CommandLineRunner {

    private Integer orderId = 0;
    private CustomerOrderService customerOrderService;
    List<String> beerUpc;

    public TimelyCustomerOrder(CustomerOrderService customerOrderService){
        this.customerOrderService = customerOrderService;
        beerUpc = new ArrayList<String>();
    }

    @Override
    public void run(String... args) throws Exception {
        beerUpc.add(DefaultBeer.BEER_1_UPC);
        beerUpc.add(DefaultBeer.BEER_2_UPC);
        beerUpc.add(DefaultBeer.BEER_3_UPC);
/*
           while(true) {
            orderId++;
            CustomerOrder customerOrder = CustomerOrder.builder()
                    .orderId(UUID.randomUUID())
                    .date(new Date(System.currentTimeMillis()))
                    .time(new Time(System.currentTimeMillis()))
                    .beerUpc(beerUpc.get(orderId % beerUpc.size()))
                    .quantity(1)
                    .customerId(String.valueOf(orderId % 3))
                    .build();
            customerOrderService.placeOrder(customerOrder);
            Thread.sleep(10000);
        }
 */
    }
}
