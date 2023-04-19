package com.global._04datajdbchrproject.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("employee")
public class Employee {

    @Id
    @Column(value = "id")
    private long id;
    @Column(value = "name")
    private String name;
    @Column(value = "salary")
    private Double salary;
    @Transient
    boolean isCreated;

    public Employee(){}

    public Employee(long id, String name, double salary,boolean isCreated){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.isCreated = isCreated;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }
}
