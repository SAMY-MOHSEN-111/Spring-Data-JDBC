package com.global._04datajdbchrproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("employee")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    @Id
    @JsonProperty("empId")
    @Column(value = "id")
    private long id;
    @JsonProperty("empName")
    @Column(value = "name")
    private String name;
    @JsonProperty("empSalary")
    @Column(value = "salary")
    private Double salary;
    @Transient // when there is a column in db that is not present in pojo use Transient
    @JsonIgnore // don't include created in json payload
    boolean created; // this field is not present in db

    public Employee() {
    }

    public Employee(long id, String name, double salary, boolean created) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.created = created;
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

    public boolean created() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", created=" + created +
                '}';
    }
}
