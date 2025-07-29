package com.systech.systech.Repository;

import com.systech.systech.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomersRepository extends JpaRepository<Customers,Long> {
    Optional<Customers> findByCustomerName(String customerName);
    List<Customers> findBySalesRep_Id(Long Id);

    List<Customers> findByCountryIgnoreCase(String country);
}
