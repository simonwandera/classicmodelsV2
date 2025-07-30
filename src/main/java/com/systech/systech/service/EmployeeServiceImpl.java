package com.systech.systech.service;

import com.systech.systech.Entity.Employee;
import com.systech.systech.Repository.AuditLogRepository;
import com.systech.systech.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuditLogService auditLogService;

    @Override
    public Employee createEmployee(Employee employee) {
        Employee save = employeeRepository.save(employee);
        auditLogService.saveOrUpdate("New Employee Created", "Employee: "+ employee.getFullName());
        return save;

    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            auditLogService.saveOrUpdate("Employee Update", "Employee: "+ employee.getFullName());
            return employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
}
