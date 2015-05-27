package com.antonnaumoff.repository.impl.jdbc;


import com.antonnaumoff.models.Employee;
import com.antonnaumoff.repository.EmployeeRepository;
import com.antonnaumoff.utils.exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class JDBCEmployeeRepository implements EmployeeRepository {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Employee> getEmloyeeListById(int dep_id) throws DataBaseException {
        ArrayList<Employee> emp = new ArrayList<Employee>();
        int id;
        String title;
        String firstName;
        String secondName;
        int salary;
        Date date;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee WHERE id_dep= (?)");
            ps.setInt(1, dep_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                title = rs.getString("title");
                firstName = rs.getString("first_name");
                secondName = rs.getString("second_name");
                salary = rs.getInt("salary");
                date = rs.getDate("date");
                Employee test = new Employee();
                test.setId(id);
                test.setDep_id(dep_id);
                test.setJob_title(title);
                test.setFirst_name(firstName);
                test.setSecond_name(secondName);
                test.setSalary(salary);
                test.setDate(date);
                emp.add(test);
            }
            ps.close();

        } catch (SQLException e) {
            throw new DataBaseException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }

    @Override
    public void deleteById(int id) throws DataBaseException {

        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Employee WHERE id=(?)");
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createEmployee(Employee emp) throws DataBaseException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Employee (id_dep, title, first_name, second_name, salary, date) " +
                            "VALUES ( ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, emp.getDep_id());
            ps.setString(2, emp.getJob_title());
            ps.setString(3, emp.getFirst_name());
            ps.setString(4, emp.getSecond_name());
            ps.setInt(5, emp.getSalary());
            ps.setDate(6, (Date)emp.getDate());
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new DataBaseException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Employee getEmloyeeById(int idd) throws DataBaseException {
        Employee emp = new Employee();
        Integer id;
        String title;
        String firstName;
        String secondName;
        int salary;
        Date date;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee WHERE id= (?)");
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                title = rs.getString("title");
                firstName = rs.getString("first_name");
                secondName = rs.getString("second_name");
                salary = rs.getInt("salary");
                date = rs.getDate("date");
                emp.setId(id);
                emp.setDep_id(idd);
                emp.setJob_title(title);
                emp.setFirst_name(firstName);
                emp.setSecond_name(secondName);
                emp.setSalary(salary);
                emp.setDate(date);
            }
            ps.close();

        } catch (SQLException e) {
            throw new DataBaseException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }

    @Override
    public void editEmployee(Employee emp) throws DataBaseException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Employee SET " +
                    "title =?, first_name = ?, second_name = ?, salary = ?, date = ? WHERE id =?");
            ps.setString(1, emp.getJob_title());
            ps.setString(2, emp.getFirst_name());
            ps.setString(3, emp.getSecond_name());
            ps.setInt(4, emp.getSalary());
            ps.setDate(5, (Date)emp.getDate());
            ps.setInt(6, emp.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DataBaseException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Integer getId_dById(Integer id) throws DataBaseException {
        Integer id_d = null;

        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_d = rs.getInt("id_dep");
            }
            ps.close();

        } catch (SQLException e) {
            throw new DataBaseException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id_d;
    }
}
