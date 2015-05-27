package com.antonnaumoff.repository.impl.jdbc;


import com.antonnaumoff.models.Department;
import com.antonnaumoff.repository.DepartmentRepository;
import com.antonnaumoff.utils.exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCDepartmentRepository implements DepartmentRepository {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Department> getAll() throws DataBaseException {

        List<Department> result = new ArrayList<Department>();

        Connection con = null;
        try {
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT * FROM Department";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("iddepartment");
                String title = rs.getString("title");
                Department department = new Department();
                department.setId(id);
                department.setTitle(title);
                result.add(department);
            }
            st.close();

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
        return result;
    }

    public void createDepartment(String title) throws DataBaseException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Department (title) VALUES (?)");
            ps.setString(1, title);
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

    public void deleteDepartment(int id) throws DataBaseException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Department WHERE idDepartment=?");
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

    public Department getDepartmentById(int id) throws DataBaseException {
        Department dep = new Department();
        String title = null;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Department WHERE idDepartment= (?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                title = rs.getString("title");
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
        dep.setTitle(title);
        dep.setId(id);
        return dep;
    }

    public void editDepartment(String test, int id) throws DataBaseException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Department SET title =? WHERE idDepartment =?");
            ps.setString(1, test);
            ps.setInt(2, id);
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
}
