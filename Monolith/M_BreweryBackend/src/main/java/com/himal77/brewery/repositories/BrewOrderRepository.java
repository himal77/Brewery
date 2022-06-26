package com.himal77.brewery.repositories;

import com.himal77.brewery.domain.BrewOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrewOrderRepository extends JpaRepository<BrewOrder, String> {
}
