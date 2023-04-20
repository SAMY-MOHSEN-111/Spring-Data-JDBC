package com.global._04datajdbchrproject.service;

import com.global._04datajdbchrproject.entity.Employee;
import com.global._04datajdbchrproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired // I heard once that if trying to inject just one bean you don't need to add @Autowired
    EmployeeServiceImpl(
            // redundant but for revision
            @Qualifier("employeeRepository")EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByNameStartingWith(name);
    }

    @Override
    public Optional<Employee> findEmployeeById(long id) {
        return employeeRepository.findById(id);// note the best practice but for learning purpose XD
    }

    @Override
    public List<Employee> findByNameAndSalary(String name, double salary) {
        return employeeRepository.findByNameContainingAndSalaryGreaterThan(name, salary);
    }

    @Override
    public long count() {
        return employeeRepository.count();
    }

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee mySave(Employee employee) {
        // if id  is null ==> insert
        return employeeRepository.mySave(employee);
    }

    @Override
    public Employee update(Employee employee) {
        // if id  is not null ==> update
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

}
