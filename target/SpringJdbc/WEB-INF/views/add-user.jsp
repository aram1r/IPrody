<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавить пользователя</title>
</head>
<body>
Authors:<br>
<form action="add" method="post" modelAttribute="user">
    Name:<br>
    <input type="text" name="name" value="name"><br>
    Email:<br>
    <input type="text" name="email" value="email"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Alex Rogov
  Date: 20.03.2026
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <table>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>email</td>
        </tr>
        <tr item="${user}" var="user}">
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
    </table>
</head>
<body>

</body>
</html>