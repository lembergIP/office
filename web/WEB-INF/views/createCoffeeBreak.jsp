<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create coffee break</title>
</head>
<body>
<h1>${date}</h1>
<form action="/createCoffeeBreak" method="post">
    <p>Time start: <input type="datetime-local" name="startTime" required="required"></p>
    <p>Time end: <input type="datetime-local" name="endTime" required="required"></p>
    <p><c:if test="${not empty error}">
        <label><div class="error" style="text-align: right;">${error}</div></label>
    </c:if></p>
    <p>Type : <strong>Coffee Break</strong>
    </p>
    <p>description :
        <input type="text" max="250" style="min-width: 300px;min-height: 150px" name="description">
    </p>
    <input type="submit" value="Create Event">
</form>
</body>
</html>
