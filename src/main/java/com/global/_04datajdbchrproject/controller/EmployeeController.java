package com.global._04datajdbchrproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global._04datajdbchrproject.entity.Employee;
import com.global._04datajdbchrproject.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// note that naming of service methods doesn't match repository methods
// naming can be improved
@RestController//@Controller+@ResponseBody
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService employeeService;

    @Autowired
    public void wireEmployeeRepository(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //    @GetMapping("/count")
    @RequestMapping(method = RequestMethod.GET, path = "/count")
    public ResponseEntity<?> countEmployees() {
        return ResponseEntity.ok(employeeService.count());
    }

    //    @GetMapping("/{id}")
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id") long id,
            @RequestHeader("accept-language") String acceptLanguage
    ) {
        logger.info(acceptLanguage);
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(optionalEmployee.orElse(null), HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(
            @RequestParam("name") String name
    ) {
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> findByNameAndSalary(
            @RequestParam("name") String name,
            @RequestParam("salary") double salary
    ) {
        return new ResponseEntity<>(employeeService.findByNameAndSalary(name, salary), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        logger.info(employee.toString());
        return new ResponseEntity<>(employeeService.mySave(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.mySave(employee));
    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable("id") long id,
            @RequestParam("name") String name,
            @RequestParam("salary") double salary
    ) {
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(id);
        if (optionalEmployee.isPresent()) {
            Employee tmpEmployee = optionalEmployee.get();
            tmpEmployee.setName(name);
            tmpEmployee.setSalary(salary);
            return employeeService.mySave(tmpEmployee);
        }
        // this is just an example for learning.
        // it could be handled better than that XD
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        employeeService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE, path = "/test")
    public ResponseEntity<?> test() {
        Resource htmlResource = new ClassPathResource("static/test.html");
        return ResponseEntity.ok(htmlResource);
    }

    public void testJackson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        long id = 2;
        String name = "ahmed mohsen";
        double salary = 1000000.0;
        boolean created = false;

        JsonNode json = objectMapper.createObjectNode()
                .put("id", id)
                .put("name", name)
                .put("salary", salary)
                .put("created", created);

        String jsonString = objectMapper.writeValueAsString(json);
        // or =>
        String jsonPayload = """
                {
                "id": 2,
                "name": "ahmed mohsen",
                "salary": 1000000.0,
                "created": false
                }
                """;

        // convert from json to pojo
        Employee employee = objectMapper.readValue(jsonPayload, Employee.class);

        // convert from pojo to json
        jsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
        System.out.println(jsonPayload);
    }
}
