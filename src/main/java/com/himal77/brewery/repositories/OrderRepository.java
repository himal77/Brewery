package com.himal77.brewery.repositories;

import com.himal77.brewery.domain.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<BeerOrder, String> {
    List<BeerOrder> findAllByCustomerId(String customerId);
    List<BeerOrder> findAllByDate(Date date);
}
