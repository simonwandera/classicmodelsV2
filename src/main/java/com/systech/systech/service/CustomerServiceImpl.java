package com.systech.systech.service;

import com.systech.systech.Entity.Customer;
import com.systech.systech.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        log.info("Fetching all customers");
        return customerRepository.findAll();
    }



    @Override
    public Optional<Customer> getById(Long id) {
        log.info("Fetching customer by ID: {}", id);
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> getByCustomerName(String name) {
        log.info("Fetching customer by name: {}", name);
        return customerRepository.findByCustomerName(name);
    }

    @Override
    public List<Customer> getBySalesRep(Long employeeId) {
        log.info("Fetching customers by sales rep ID: {}", employeeId);
        return customerRepository.findBySalesRepEmployeeNumber_Id(employeeId);
    }

    @Override
    public List<Customer> getByCountry(String country) {
        log.info("Fetching customers from country: {}", country);
        return customerRepository.findByCountryIgnoreCase(country);
    }

    @Override
    public Customer create(Customer customer) {
        log.info("Creating customer: {}", customer.getCustomerName());
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> update(Long id, Customer updatedCustomer) {
        log.info("Updating customer with ID: {}", id);
        return customerRepository.findById(id).map(existing -> {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        });
    }

    @Override
    public boolean delete(Long id) {
        log.info("Deleting customer with ID: {}", id);
        return customerRepository.findById(id).map(existing -> {
            customerRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
