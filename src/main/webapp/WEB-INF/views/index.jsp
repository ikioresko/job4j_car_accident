<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Text</th>
        <th scope="col">Address</th>
        <th scope="col">UPD</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accidents}" var="acc">
        <tr>
            <td><c:out value="${acc.id}"/></td>
            <td><c:out value="${acc.name}"/></td>
            <td><c:out value="${acc.text}"/></td>
            <td><c:out value="${acc.address}"/></td>
            <td><a href="<c:url value='/edit?id=${acc.id}'/>">Редактировать инцидент</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href='<c:url value="/create"/>'>Добавить инцидент</a>
</body>
</html>