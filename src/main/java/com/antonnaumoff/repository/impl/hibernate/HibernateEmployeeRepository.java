package com.antonnaumoff.repository.impl.hibernate;


import com.antonnaumoff.models.Employee;
import com.antonnaumoff.repository.EmployeeRepository;
import com.antonnaumoff.utils.exceptions.DataBaseException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateEmployeeRepository implements EmployeeRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getEmloyeeListById(int id) throws DataBaseException {

        Session session = sessionFactory.getCurrentSession();

        String hql = "from Employee where id_dep =:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        //noinspection unchecked
        List<Employee> list = (List<Employee>) q.list();

        return list;
    }

    @Override
    public void deleteById(int id) throws DataBaseException {

        Session session = sessionFactory.getCurrentSession();
        Employee emp = (Employee) session.load(Employee.class, id);
        session.delete(emp);

    }

    @Override
    public void createEmployee(Employee emp) throws DataBaseException {

        Session session = sessionFactory.getCurrentSession();
        session.save(emp);

    }

    @Override
    public Employee getEmloyeeById(int id) throws DataBaseException {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee where id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        Employee emp = (Employee) q.uniqueResult();

        return emp;
    }

    @Override
    public void editEmployee(Employee emp) throws DataBaseException {

        Session session = sessionFactory.getCurrentSession();

        String hql = "UPDATE Employee SET job_title=:title,first_name=:firstName,second_name=:secondName,salary=:salary,date=:date WHERE id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("title", emp.getJob_title());
        q.setParameter("firstName", emp.getFirst_name());
        q.setParameter("secondName", emp.getSecond_name());
        q.setParameter("salary", emp.getSalary());
        q.setParameter("date", emp.getDate());
        q.setParameter("id", emp.getId());
        q.executeUpdate();

    }

    @Override
    public Integer getId_dById(Integer id) throws DataBaseException {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee where id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        Integer id_d = ((Employee) q.uniqueResult()).getDep_id();

        return id_d;
    }
}
