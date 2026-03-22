<%--
  Created by IntelliJ IDEA.
  User: Alex Rogov
  Date: 22.03.2026
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пользователь с id</title>
</head>
<body>
Authors:<br>
<table border="1">
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>email</th>
    </tr>
    <c:out items="${user}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
    </c:out>
</table>
</body>
</html>
