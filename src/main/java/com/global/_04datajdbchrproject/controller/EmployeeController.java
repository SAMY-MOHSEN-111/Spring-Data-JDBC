package com.global._04datajdbchrproject.controller;

import com.global._04datajdbchrproject.entity.Employee;
import com.global._04datajdbchrproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    @Autowired
    public void wireEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/count")
    public long countEmployees() {
        return employeeRepository.count();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    @GetMapping("/findByName")
    public List<Employee> findByName(
            @RequestParam("name") String name
    ) {
        return employeeRepository.findByNameStartingWith(name);
    }

    @GetMapping("/filter")
    public List<Employee> findByNameAndSalary(
            @RequestParam("name")String name,
            @RequestParam("salary")double salary
    ){
        return employeeRepository.findByNameContainingAndSalaryGreaterThan(name,salary);
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/update/{id}")
    public Employee update(
            @PathVariable("id") long id,
            @RequestParam("name") String name,
            @RequestParam("salary") double salary
    ) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee tmpEmployee = optionalEmployee.get();
            tmpEmployee.setName(name);
            tmpEmployee.setSalary(salary);
            return employeeRepository.save(tmpEmployee);
        }
        // this is just an example for learning.
        // it could be handled better than that XD
        return null;
    }
}
