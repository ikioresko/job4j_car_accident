<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Full Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td><c:out value="${user}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>