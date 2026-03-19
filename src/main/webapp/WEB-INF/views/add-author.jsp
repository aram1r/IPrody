<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавить автора</title>
</head>
<body>
    Authors:<br>
    <form action="add" method="post" modelAttribute="author">
        Name:<br>
        <input type="text" name="name" value="name"><br>
        Email:<br>
        <input type="text" name="country" value="country"><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>