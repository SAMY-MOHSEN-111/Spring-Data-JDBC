package com.global._04datajdbchrproject.repository;

import com.global._04datajdbchrproject.entity.Employee;
import com.global._04datajdbchrproject.mapper.EmployeeMapper;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByNameStartingWith(String name);

//    @Query(value = "select * from `hr-global`.employee where name like concat('%',:empName,'%') and salary >= :empSalary")
//    List<Employee> findByNameContainingAndSalaryGreaterThan(
//            @Param("empName") String name,
//            @Param("empSalary") double salary)

    @Query(
            value = "select * from `hr-global`.employee where name like concat('%',:name,'%') and salary >= :salary",
            rowMapperClass = EmployeeMapper.class
    )
// the question is why would I use mapper if I could just select what I want in the query?
    List<Employee> findByNameContainingAndSalaryGreaterThan(
            String name,
            double salary
    );

    @Modifying
    @Query(value = "update `hr-global`.employee set" +
            " name = :#{#employee.name}," +
            " salary = :#{#employee.salary}" +
            " where id = :#{#employee.id}")
    Employee mySave(@Param("employee") Employee employee);
}
