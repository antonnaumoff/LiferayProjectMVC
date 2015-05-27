package com.antonnaumoff.repository.impl.hibernate;


import com.antonnaumoff.models.Department;
import com.antonnaumoff.repository.DepartmentRepository;
import com.antonnaumoff.utils.exceptions.DataBaseException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateDepartmentRepository implements DepartmentRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Department> getAll() throws DataBaseException {

        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from Department");

        return (List<Department>) q.list();
    }

    @Override
    public void createDepartment(String title) throws DataBaseException {

        Department dep = new Department();
        dep.setTitle(title);
        Session session = sessionFactory.getCurrentSession();
        session.save(dep);
    }

    @Override
    public void deleteDepartment(int id) throws DataBaseException {

        Session session = sessionFactory.getCurrentSession();
        Department dep = (Department) session.load(Department.class, id);
        session.delete(dep);

    }

    @Override
    public Department getDepartmentById(int id) throws DataBaseException {

            Session  session = sessionFactory.getCurrentSession();
            String hql = "from Department where idDepartment=:id";
            Query q = session.createQuery(hql);
            q.setParameter("id", id);

        return (Department) q.uniqueResult();
    }

    @Override
    public void editDepartment(String test, int id) throws DataBaseException {

            Session session = sessionFactory.getCurrentSession();
            String hql = "UPDATE Department SET title =:test WHERE idDepartment =:id";
            Query q = session.createQuery(hql);
            q.setParameter("test", test);
            q.setParameter("id", id);
            q.executeUpdate();

    }
}
