<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<html>
<head>
    <%--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">--%>
</head>
<body>

<portlet:defineObjects/>

<portlet:actionURL var="create">
    <portlet:param name="action" value="validateCreation"/>
</portlet:actionURL>

<portlet:actionURL var="edit">
    <portlet:param name="action" value="validateEditing"/>
</portlet:actionURL>


<div class="panel-heading">
    <div class="custom">${not empty department.id && department.id!=0? 'Edit Department' : 'Create Department' }</div>
</div>
<div class="container-fluid">
    <form class="form" role="form"
          action="${not empty department.id && department.id!=0 ? edit : create}"
          method="POST">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="<portlet:namespace />title"
                   value='${department.title}'/>
            <input type="hidden" id="id" name="<portlet:namespace />id_dep"
                   value="${not empty department.id? department.id:0}">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
        <span class="alarma">${message}</span>
    </form>
</div>
</body>
</html>

