package com.antonnaumoff.models;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * Created on 23.03.15.
 */
@Entity
@Table(name = "Department")

@Guarded
public class Department implements Serializable {

    @Id
    @Column(name = "idDepartment")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    @Column(name = "title")

    @NotNull(message = "Null")
    @NotEmpty(message = "Title cannot be empty")
    @Length(max = 36, message = "Title max length 36 chars")
    private String title;

    @OneToMany(mappedBy = "department", cascade=CascadeType.ALL)
    private Set<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
