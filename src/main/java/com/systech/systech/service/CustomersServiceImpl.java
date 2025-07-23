package com.systech.systech.service;

import com.systech.systech.Entity.Customers;
import com.systech.systech.Repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomersServiceImpl implements CustomersService{

    private final CustomersRepository customersRepository;

    @Override
    public List<Customers> getAll() {
        log.info("Fetching all customers");
        return customersRepository.findAll();
    }

    @Override
    public Optional<Customers> getById(Long id) {
        log.info("Fetching customer by ID: {}", id);
        return customersRepository.findById(id);
    }

    @Override
    public Optional<Customers> getByCustomerName(String name) {
        log.info("Fetching customer by name: {}", name);
        return customersRepository.findByCustomerName(name);
    }

    @Override
    public List<Customers> getBySalesRep(Long employeeId) {
        log.info("Fetching customers by sales rep ID: {}", employeeId);
        return customersRepository.findBySalesRep_Id(employeeId);
    }

    @Override
    public List<Customers> getByCountry(String country) {
        log.info("Fetching customers from country: {}", country);
        return customersRepository.findByCountryIgnoreCase(country);
    }

    @Override
    public Customers create(Customers customer) {
        log.info("Creating customer: {}", customer.getCustomerName());
        return customersRepository.save(customer);
    }

    @Override
    public Optional<Customers> update(Long id, Customers updatedCustomer) {
        log.info("Updating customer with ID: {}", id);
        return customersRepository.findById(id).map(existing -> {
            updatedCustomer.setId(id);
            return customersRepository.save(updatedCustomer);
        });
    }

    @Override
    public boolean delete(Long id) {
        log.info("Deleting customer with ID: {}", id);
        return customersRepository.findById(id).map(existing -> {
            customersRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
