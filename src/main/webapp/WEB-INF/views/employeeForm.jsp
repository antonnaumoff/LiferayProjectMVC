<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>

<portlet:defineObjects/>

<portlet:actionURL var="create">
    <portlet:param name="action" value="validateEmpCreation"/>
</portlet:actionURL>

<portlet:actionURL var="edit">
    <portlet:param name="action" value="validateEmpEditing"/>
</portlet:actionURL>

<body>
<div class="panel-heading">
    <div class="custom">${empty editor ? 'Create Employee':'Edit Employee'}</div>
</div>
<div class="container-fluid">
    <form class="form" role="form"
          action="${empty editor ? create : edit}"
          method="POST">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="<portlet:namespace />job_title"
                   value='${emp.job_title}'/>
        </div>
        <span class="alarma">${messages.job_title}</span>

        <div class="form-group">
            <label class="contact_form_label" for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstName" name="<portlet:namespace />first_name"
                   value='${emp.first_name}'/>
        </div>
        <span class="alarma">${messages.first_name}</span>

        <div class="form-group">
            <label class="contact_form_label" for="secondName">Second Name:</label>
            <input type="text" class="form-control" id="secondName" name="<portlet:namespace />second_name"
                   value='${emp.second_name}'/>
        </div>
        <span class="alarma">${messages.second_name}</span>

        <div class="form-group">
            <label class="contact_form_label" for="salary">Salary:</label>
            <input type="text" class="form-control" id="salary" name="<portlet:namespace />salary"
                   value='${emp.salary}'/>
        </div>
        <span class="alarma">${messages.salary}</span>

        <div class="form-group">
            <label class="contact_form_label" for="date">Date:</label>
            <input type="date" class="form-control" id="date" name="<portlet:namespace />date" value='${emp.date}'/>
            <input type="hidden" id="id" name="<portlet:namespace />id" value='${not empty emp.id? emp.id:0}'/>
            <input type="hidden" name="<portlet:namespace />id_dep" value="${id_dep}"/>
        </div>
        <span class="alarma">${messages.date} </span>
        <button type="submit" class="btn btn-default">Submit</button>

        <div class="alarma">
            ${message}
        </div>
    </form>
</div>
</body>
</html>

