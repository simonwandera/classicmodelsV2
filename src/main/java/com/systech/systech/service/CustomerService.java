package com.systech.systech.service;

import com.systech.systech.Entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();

    Optional<Customer> getById(Long id);

    Optional<Customer> getByCustomerName(String name);

    List<Customer> getBySalesRep(Long employeeId);

    List<Customer> getByCountry(String country);

    Customer create(Customer customer);

    Optional<Customer> update(Long id, Customer updatedCustomer);

    boolean delete(Long id);




}
