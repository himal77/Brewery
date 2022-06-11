package com.himal77.brewery.repositories;

import com.himal77.brewery.domain.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<BeerOrder, String> {
}
