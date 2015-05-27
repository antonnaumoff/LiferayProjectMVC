<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/css/my.css"/>" type="text/css">
</head>
<body>
<div class="row">
    <div class="col-sm-3">
    </div>
    <div class="col-sm-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="error"><p>

                    <div class="custom">Ooops... Page not found</div>

                    <p></div>
            </div>
            <div class="alarma">
                <h3><p class="error">${message}</p></h3>
            </div>

        </div>

    </div>
    <div class="col-sm-3">
    </div>
</div>
</body>
</html>
