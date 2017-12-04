
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
<div>
    <form action="/test" method="post" name="changePassword">

        <p>
            <label for="correctPassword">Correct password : </label>
            <input id="correctPassword" name="correctPassword" required="required" type="password"  />
        </p>


        <p class="signin button">
            <input type="submit" value="Update Password"/>

        </p>

        </p>

    </form >
</div>
</body>
</html>
