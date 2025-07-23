package com.systech.systech.service;

import com.systech.systech.Entity.Employee;


import java.util.List;


public interface EmployeeServiceI {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id); 

}