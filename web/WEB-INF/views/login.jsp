<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Login</title>

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="resources/css/login/animate.css">
    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href="resources/css/login/style.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body style="background: url(resources/images/login/photo_bg.jpg) no-repeat center center fixed;">
<div class="container">

    <div class="login-box animated fadeInUp">
        <div class="box-header">
            <h2>Log In</h2>
        </div>
        <form action="j_spring_security_check" method="post" autocomplete="on">
        <label for="username">Username</label>
        <br/>
        <input type="text" id="username" name="j_username">
        <br/>
        <label for="password">Password</label>
        <br/>
        <input type="password" id="password" name="j_password">
        <br/>
        <c:if test="${not empty error}">
            <label><div class="error" style="text-align: right;">${error}</div></label>
        </c:if>
        <br/>
        <button type="submit">Logg In</button>
        <br/>
        </form>
    </div>
</div>
</body>

<script src="resources/js/login.js"></script>

</html>
