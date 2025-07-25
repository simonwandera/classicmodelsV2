package com.systech.systech.service;

import com.systech.systech.Entity.Customers;
import com.systech.systech.Repository.CustomersRepository;

import java.util.List;
import java.util.Optional;

public interface CustomersService {
    List<Customers> getAll();

    Optional<Customers> getById(Long id);

    Optional<Customers> getByCustomerName(String name);

    List<Customers> getBySalesRep(Long employeeId);

    List<Customers> getByCountry(String country);

    Customers create(Customers customer);

    Optional<Customers> update(Long id, Customers updatedCustomer);

    boolean delete(Long id);


}
