<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html >
<head>
    <meta charset="utf-8">
    <title>Menu page</title>

    <!-- Google Web Font Embed -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,600,500,300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/templatemo_main.css">
    <link rel="stylesheet" href="resources/css/registrationUser.css">

</head>
<body style="background: url(resources/images/home/bg.jpg) no-repeat center center fixed;">
<div id="main-wrapper">
    <jsp:include page="layout/header.jsp"/>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 col-lg-offset-4 col-md-offset-4 templatemo-content-wrapper">

            <div class="templatemo-content">

                <section id="home-text" class="active templatemo-content-section">
                    <div class="col-sm-12 col-md-12 col-lg-12">
                        <form  action="updateUser/info" method="post" autocomplete="on">
                            <h2 style="text-align: center"> Update your info </h2>
                            <hr>
                            <div class="regist" style="margin-right: 15%;float: left">
                                <label for="firstName">Name</label>
                                <input id="firstName" name="firstName" required="required" type="text" value="${user.firstName}" />
                            </div>
                            <div class="regist" style="margin-left: 5%;float: none">
                                <label for="lastName"> Surname</label>
                                <input id="lastName" name="lastName" required="required" type="text" value="${user.lastName}" />
                            </div>
                            <hr>
                            <div class="regist" style="margin-right: 5%;float: left">
                                <label for="dateBirth" >Date Birth</label>
                                <input id="dateBirth" name="dateBirth" required="required" type="date" value="${user.dateBirth}"/>
                            </div>
                            <div class="regist" style="margin-right: 15%;float: none">
                                <label for="phoneNumber" >Telephone number</label>
                                <input id="phoneNumber" name="phoneNumber" required="required" type="text" value="${user.phoneNumber}"/>
                            </div>
                            <hr>
                            <div class="fo" style="float: right;margin-right: 5%">
                                    <input type="submit" value="Update Info"/>
                                <a href="/findUserById${user.id}"><button type="button" value="Cancel">Cancel</button></a>
                            </div>

                            </p>
                        </form>

                    </div>

                    <hr>
                    <div >
                        <form action="updateUser/password" method="post" name="changePassword" onsubmit="return validateForm()">
                            <div style="margin-left: 10%;margin-right: auto ">
                            <label for="correctPassword">Correct password : </label><br>
                            <input id="correctPassword" name="correctPassword" required="required" type="password"  />
                            <c:if test="${not empty error1}">
                                <label><div class="error" style="text-align: left;color: red">${error1}</div></label>
                            </c:if>
                            </div>
                            <br>
                            <div style="margin-left: 15%;margin-right: auto ">
                            <label for="newPassword"> New password : </label> <br>
                            <input id="newPassword" name="newPassword" required="required" type="password"  /> <br>
                            </div>
                            <div style="margin-left: 20%;margin-right: auto ">
                            <label for="confirmNewPassword" >Confirm new password : </label> <br>
                            <input id="confirmNewPassword" name="confirmNewPassword" required="required" type="password" /><br><hr>
                            </div>
                            <div style="margin-left: 25%;margin-right: auto ">
                            <input type="submit" value="Update Password"/>
                            </div>
                        </form >
                    </div>
                </section>
            </div>
        </div>
    </div>
    <jsp:include page="layout/footer.jsp"/>

</div><!-- /#main-wrapper -->
</body>
</html>


