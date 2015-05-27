package com.antonnaumoff.utils.forms;

import net.sf.oval.constraint.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class EmployeeForm extends Form {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_dep() {
        return id_dep;
    }

    public void setId_dep(Integer id_dep) {
        this.id_dep = id_dep;
    }

    private Integer id;

    private Integer id_dep;

    @NotNull(message = "Your Title is null")
    @NotEmpty(message = "Your Title is blank")
    @Length(max = 32, message = "max digits = 32")
    private String jobTitle;

    @NotNull(message = "Your First Name is null")
    @NotEmpty(message = "Your First Name is blank")
    @Length(max = 32, message = "max digits = 32")
    private String firstName;

    @NotNull(message = "Your Second Name is null")
    @NotEmpty(message = "Your Second Name is blank")
    @Length(max = 32, message = "max digits = 32")
    private String secondName;

    @NotNull(message = "Your salary is blank")
    @Range(min = 10, max = 1000000, message = "Enter value in a range from 10 to 1000000")
    @NotEmpty(message = "Your Salary is blank")
    private Integer salary;

    @NotNull(message = "Your date is incorrect, please, enter a value in a range from 1990-01-01 till now")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateRange(format = "yyyy-MM-dd", min = "1990-01-01", max = "now", message = "Enter a date between 1990-01-01 and today")
    private Date date;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
