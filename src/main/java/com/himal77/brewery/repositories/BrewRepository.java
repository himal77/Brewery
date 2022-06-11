package com.himal77.brewery.repositories;

import com.himal77.brewery.domain.Brew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrewRepository extends JpaRepository<Brew, String> {
}
