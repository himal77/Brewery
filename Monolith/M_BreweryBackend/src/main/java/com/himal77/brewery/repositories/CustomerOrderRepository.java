package com.himal77.brewery.repositories;

import com.himal77.brewery.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, String> {
    List<CustomerOrder> findAllByCustomerId(String customerId);
    List<CustomerOrder> findAllByDate(Date date);
}
