package com.himal77.brewery.repositories;

import com.himal77.brewery.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
