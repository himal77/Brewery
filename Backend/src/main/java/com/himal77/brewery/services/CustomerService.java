package com.himal77.brewery.services;

import com.himal77.brewery.domain.Customer;
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

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findAllCustomerByCustomerId(String customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer, String customerId) {
        customer.setCustomerId(customerId);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
