<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" type="text/css">
</head>
<body>

<portlet:defineObjects/>

<portlet:renderURL var="create">
    <portlet:param name="action" value="createDepartment"/>
</portlet:renderURL>

<portlet:actionURL var="delete">
    <portlet:param name="action" value="deleteDepartment"/>
</portlet:actionURL>

<portlet:renderURL var="edit">
    <portlet:param name="action" value="editDepartment"/>
</portlet:renderURL>

<portlet:renderURL var="listEmployee">
    <portlet:param name="action" value="listEmployees"/>
</portlet:renderURL>


<div class="panel-heading">
    <div class="custom">Departments List</div>
</div>
<table class="table-striped">
    <thead>
    <tr>
        <td class="table-header">Title</td>
        <td class="my-table-cell3"></td>
        <td class="my-table-cell3"></td>
        <td class="my-table-cell3">
            <form action="${create}" method="post">
                <button type="submit" class="btn btn-default btn-lg">
                    <span class="glyphicon glyphicon-plus"></span>
                </button>
            </form>
        </td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dep" items="${department}">
        <tr>
            <td class="my-table-cell"><c:out value="${dep.title}"/></td>

            <td class="my-table-cell3">
                <form action="${delete}" method="post">
                    <input type="hidden" name="<portlet:namespace/>id_dep" value="${dep.id}"/>
                    <button type="submit" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                </form>
            </td>
            <td class="my-table-cell3">
                <form action="${edit}" method="post">
                    <input type="hidden" name="<portlet:namespace/>id_dep" value="${dep.id}"/>
                    <button type="submit" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                </form>

            </td>

            <td class="my-table-cell3">
                <form action="${listEmployee}" method="post">
                    <input type="hidden" name="<portlet:namespace/>id_dep" value="${dep.id}"/>
                    <button type="submit" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-th-list"></span>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
