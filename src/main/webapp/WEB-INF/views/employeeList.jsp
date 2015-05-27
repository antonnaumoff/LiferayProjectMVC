<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">--%>
</head>
<body>

<portlet:defineObjects/>

<portlet:renderURL var="create">
    <portlet:param name="action" value="createEmployee"/>
</portlet:renderURL>

<portlet:actionURL var="delete">
    <portlet:param name="action" value="deleteEmployee"/>
</portlet:actionURL>

<portlet:renderURL var="edit">
    <portlet:param name="action" value="editEmployee"/>
</portlet:renderURL>

<portlet:renderURL var="listDepartments">
    <portlet:param name="action" value="departmentList"/>
</portlet:renderURL>

<div class="panel-heading">
    <div class="custom">Employee List</div>
</div>
<table class="table-hover">
    <thead>
    <tr>
        <td class="table-header">Title</td>
        <td class="table-header">First Name</td>
        <td class="table-header">Second Name</td>
        <td class="table-header">Salary</td>
        <td class="table-header">Date</td>
        <td class="my-table-cell3">
            <form action="${create}" method="post">
                <input type="hidden" name="<portlet:namespace/>id_dep" value="${id_dep}"/>
                <button type="submit" class="btn btn-default btn-lg">
                    <span class="icon-plus"></span>
                </button>
            </form>
        </td>
        <td class="my-table-cell3">

            <form action="${listDepartments}" method="post">
                <input type="hidden" name="<portlet:namespace/>id_dep" value="${id_dep}"/>
                <button type="submit" class="btn btn-default btn-lg">
                    <span class="icon-list"></span>
                </button>
            </form>
        </td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="emp" items="${list}">
        <tr>
            <td class="my-table-cell"><c:out value="${emp.job_title}"/></td>

            <td class="my-table-cell"><c:out value="${emp.first_name}"/></td>

            <td class="my-table-cell"><c:out value="${emp.second_name}"/></td>

            <td class="my-table-cell"><c:out value="${emp.salary}"/></td>

            <td class="my-table-cell"><c:out value="${emp.date}"/></td>

            <td class="my-table-cell3">
                <form action="${delete}"
                      method="post">
                    <input type="hidden" name="<portlet:namespace/>id" value="${emp.id}"/>
                    <input type="hidden" name="<portlet:namespace/>id_dep" value="${id_dep}">
                    <button type="submit" class="btn btn-default btn-lg">
                        <span class="icon-remove"></span>
                    </button>
                </form>
            </td>

            <td class="my-table-cell3">
                <form action="${edit}" method="post">
                    <input type="hidden" name="<portlet:namespace/>id" value="${emp.id}"/>
                    <button type="submit" class="btn btn-default btn-lg">
                        <span class="icon-pencil"></span>
                    </button>
                </form>
            </td>

        </tr>
    </c:forEach>
    </tbody>

</table>
</body>


