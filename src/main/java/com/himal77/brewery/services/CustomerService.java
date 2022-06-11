package com.himal77.brewery.services;

import com.himal77.brewery.domain.Brewery;
import com.himal77.brewery.domain.Customer;
import com.himal77.brewery.repositories.BreweryRepository;
import com.himal77.brewery.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(String customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
