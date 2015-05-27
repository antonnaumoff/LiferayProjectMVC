package com.antonnaumoff.service;

import com.antonnaumoff.models.Department;
import com.antonnaumoff.models.Employee;
import com.antonnaumoff.repository.DepartmentRepository;
import com.antonnaumoff.repository.EmployeeRepository;
import com.antonnaumoff.utils.exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class DataServiceImpl implements DataService {

    @Autowired
    @Qualifier("hibernateDepartmentRepository")
    private DepartmentRepository departmentRepository;/* = new HibernateDepartmentRepository();*/

    @Autowired
    @Qualifier("hibernateEmployeeRepository")
    private EmployeeRepository employeeRepository;/* = new HibernateEmployeeRepository();*/

    @Override
    @Transactional(readOnly = true)
    public List<Department> getDepartmentList() throws DataBaseException {
        return departmentRepository.getAll();
    }

    @Override
    public void createDepartment(String title) throws DataBaseException {
        departmentRepository.createDepartment(title);
    }

    @Override
    public void deleteDepartment(int id) throws DataBaseException {
        departmentRepository.deleteDepartment(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(int id) throws DataBaseException {
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public void editDepartment(Department dep) throws DataBaseException {
        departmentRepository.editDepartment(dep.getTitle(), dep.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmloyeeListById(int id) throws DataBaseException {
        return employeeRepository.getEmloyeeListById(id);
    }

    @Override
    public void deleteById(int id) throws DataBaseException {
        employeeRepository.deleteById(id);
    }

    @Override
    public void createEmployee(Employee emp) throws DataBaseException {
        employeeRepository.createEmployee(emp);
    }

    @Override
    public Employee getEmloyeeById(int id) throws DataBaseException {
        return employeeRepository.getEmloyeeById(id);
    }

    @Override
    public void editEmployee(Employee emp) throws DataBaseException {
        employeeRepository.editEmployee(emp);
    }

    @Override
    public Integer getId_dById(Integer id) throws DataBaseException {
        return employeeRepository.getId_dById(id);
    }
}
