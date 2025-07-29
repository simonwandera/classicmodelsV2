package com.systech.systech.Repository;

import com.systech.systech.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByCustomerName(String customerName);
    List<Customer> findBySalesRepEmployeeNumber_Id(Long employeeId);

    List<Customer> findByCountryIgnoreCase(String country);
}
