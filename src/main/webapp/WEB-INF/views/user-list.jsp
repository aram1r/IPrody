
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
Authors:<br>
<table border="1">
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>email</th>
    </tr>
    <c:forEach items="${users}" var="users">
        <tr>
            <td>${users.id}</td>
            <td>${users.name}</td>
            <td>${users.email}</td>
        </tr>
    </c:forEach>
</table>
<a href="users/add">Добавить пользователя</a>
</body>
</html>