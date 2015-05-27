package com.antonnaumoff.models;

import net.sf.oval.constraint.*;
import net.sf.oval.guard.Guarded;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Employee")

@Guarded
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    @Column(name = "id_dep")
    private Integer dep_id;

    @Column(name = "title")

    @NotNull(message = "Your Title is null")
    @NotEmpty(message = "Your Title is blank")
    @Length(max = 32, message = "max digits = 32")
    private String job_title;

    @Column(name = "first_name")

    @NotNull(message = "Your First Name is null")
    @NotEmpty(message = "Your First Name is blank")
    @Length(max = 32, message = "max digits = 32")
    private String first_name;

    @Column(name = "second_name")

    @NotNull(message = "Your Second Name is null")
    @NotEmpty(message = "Your Second Name is blank")
    @Length(max = 32, message = "max digits = 32")
    private String second_name;

    @Column(name = "salary")

    @Range(min = 10, max = 1000000, message = "Enter value in a range from 10 to 1000000")
    @NotEmpty(message = "Your Salary is blank")
    private Integer salary;

    @Column(name = "date")
    @Temporal(value = TemporalType.DATE)

    @DateRange(format = "yyyy-mm-dd", min = "1990-01-01", max = "now", message = "Enter a date between 01-01-1990 and today")
    private java.util.Date date;

    @ManyToOne
    @JoinColumn(name="id_dep", insertable=false, updatable=false)
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!date.equals(employee.date)) return false;
        if (!first_name.equals(employee.first_name)) return false;
        if (!job_title.equals(employee.job_title)) return false;
        if (!salary.equals(employee.salary)) return false;
        if (!second_name.equals(employee.second_name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = job_title.hashCode();
        result = 31 * result + first_name.hashCode();
        result = 31 * result + second_name.hashCode();
        result = 31 * result + salary.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
