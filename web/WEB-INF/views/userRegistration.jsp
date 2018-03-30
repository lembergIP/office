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
                        <form  action="adm-registration" method="post" autocomplete="on">
                            <h2 style="text-align: center"> Sign up </h2>
                              <hr>
                            <div class="regist" style="margin-right: 15%;float: left">
                                <label for="namesignup" class="uname" data-icon="u"><span>Name</span></label>
                                <input id="namesignup" name="firstName" required="required" type="text" placeholder="FIRST_NAME" />
                            </div>
                                <div class="regist" style="margin-left: 5%;float: none">
                                <label for="surnamesignup" class="uname" data-icon="u"><span>Surname</span></label>
                                <input id="surnamesignup" name="lastName" required="required" type="text" placeholder="SECOND_NAME" />
                            </div>
                            <hr>
                            <div class="regist" style="margin-right: 15%;float: left">
                                <label for="emailsignup" class="youmail" data-icon="e" ><span>Email</span></label>
                                <input id="emailsignup" name="email" required="required" type="email" placeholder="EXAMPLE@MAIL.COM"/>
                                <c:if test="${not empty error}">
                                    <label><div class="error" style="text-align: right;">${error}</div></label>
                                </c:if>
                            </div>
                            <div class="regist" style="margin-left: 5%;float: none">
                                <label for="passwordsignup" class="youpasswd" data-icon="p"><span>Password</span></label>
                                <input id="passwordsignup" name="password" required="required" type="password" placeholder="eg.tyX8df!90EO"/>
                            </div>
                            <hr>
                            <div class="regist" style="margin-right: 5%;float: left">
                                <label for="dateBirth" class="dateBirth" data-icon="p"><span>Date Birth</span></label>
                                <input id="dateBirth" name="dateBirth" required="required" type="date" />

                            </div>
                            <div class="regist" style="margin-right: 15%;float: none">
                                <label for="phoneNumber" class="phoneNumber" data-icon="p"><span>Telephone number</span></label>
                                <input id="phoneNumber" name="phoneNumber" required="required" type="text" />
                            </div>
                            <hr>
                            <div class="regist">
                                <label for="role" class="role" data-icon="p"><span>Select role</span></label>
                                <select id="role" name="role">
                                    <c:forEach items="${roleList}" var="role">
                                        <option value="${role}">${role}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <hr>
                   <div class="fo" style="float: right;margin-right: 5%">
                                <input type="submit" value="Sign up"/>
                             <a href="#"> <button type="reset">Cancel</button></a>
                   </div>

                            </p>
                        </form>

                    </div>

                </section>
            </div>
        </div>
    </div>
    <jsp:include page="layout/footer.jsp"/>

</div><!-- /#main-wrapper -->
</body>
<script src="resources/js/validatePhoneDate.js"></script>
<script src="resources/js/jquery-3.1.1.js" type="text/javascript"></script>
<script src="resources/js/maskedinput.js" type="text/javascript"></script>
<script>
    jQuery(function($){
        $("#phoneNumber").mask("(999)999-99-99");
    });
</script>
</html>

