package com.antonnaumoff.repository;


import com.antonnaumoff.models.Employee;
import com.antonnaumoff.utils.exceptions.DataBaseException;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> getEmloyeeListById(int id) throws DataBaseException;

    void deleteById(int id) throws DataBaseException;

    void createEmployee(Employee emp) throws DataBaseException;

    Employee getEmloyeeById(int id)throws DataBaseException;

    void editEmployee(Employee emp) throws DataBaseException;

    Integer getId_dById(Integer id) throws DataBaseException;
}
