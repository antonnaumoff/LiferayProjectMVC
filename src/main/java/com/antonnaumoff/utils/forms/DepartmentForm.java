package com.antonnaumoff.utils.forms;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

public class DepartmentForm extends Form{

    @NotNull(message = "Title must be correct")
    @NotEmpty(message = "Title must not be empty")
    @Length(max = 24, message = "Title must be more then 24 characters")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private Integer id_dep;

    public Integer getId_dep() {
        return id_dep;
    }

    public void setId_dep(Integer id_dep) {
        this.id_dep = id_dep;
    }
}
