package com.global._04datajdbchrproject.service;

import com.global._04datajdbchrproject.entity.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {
    List<Employee> findByName(String name);

    Optional<Employee> findEmployeeById(long id);

    List<Employee> findByNameAndSalary(String name, double salary);

    long count();

    List<Employee> findAll();

    Employee mySave(Employee employee);

    Employee update(Employee employee);

    void delete(long id);
}
