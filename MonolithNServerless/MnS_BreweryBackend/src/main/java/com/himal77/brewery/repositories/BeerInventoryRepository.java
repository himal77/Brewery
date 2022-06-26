package com.himal77.brewery.repositories;

import com.himal77.brewery.domain.BeerInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerInventoryRepository extends JpaRepository<BeerInventory, String> {
}
