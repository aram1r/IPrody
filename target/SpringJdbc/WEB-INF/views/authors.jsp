<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Список авторов</title>
    </head>
    <body>
        Authors:<br>
            <table border="1">
                <tr>
                    <th>id</th>
                    <th>Имя</th>
                    <th>Страна</th>
                </tr>
                <c:forEach items="${authors}" var="author">
                    <tr>
                        <td>${author.id}</td>
                        <td>${author.name}</td>
                        <td>${author.country}</td>
                    </tr>
                </c:forEach>
            </table>
            <a href="authors/add">Добавить пользователя</a>
    </body>
</html>