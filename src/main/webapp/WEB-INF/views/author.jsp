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
    <title>Author</title>
    <table>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>country</td>
        </tr>
        <tr item="${author}" var="author}">
            <td>${author.id}</td>
            <td>${author.name}</td>
            <td>${author.country}</td>
        </tr>
    </table>
</head>
<body>

</body>
</html>
