
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html >
<head>
    <meta charset="utf-8">
    <title>Menu page</title>

    <!-- Google Web Font Embed -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,600,500,300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/templatemo_main.css">

</head>
<body style="background: url(resources/images/home/bg.jpg) no-repeat center center fixed;">
<div id="main-wrapper">
    <jsp:include page="layout/header.jsp"/>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 col-lg-offset-4 col-md-offset-4 templatemo-content-wrapper">
            <div>
                Find user by email:
                <form action="findUserByEmail">
                    <input type="search" id="findUserByEmail" name="findUserByEmail">
                    <input type="submit" value="Search">
                </form>
            </div>
            <div class="templatemo-content">

                <section id="home-text" class="active templatemo-content-section">
                    <div class="col-sm-12 col-md-12 col-lg-12">
                        <h2>${correctUser.firstName} ${correctUser.lastName}
                            <button type="button" class="btn btn-default" aria-label="Left Align" style="margin-left: 5%">
                            <a href="/updateUser">   <span class="glyphicon glyphicon-cog" aria-hidden="true" style="float: left" ></span></a>
                        </button></h2 style="text-align: center">
                        <hr>
                        <div class="col-sm-4 col-md-4">
                            <div id="Photo">
                                <img id="picture" class="img-thumbnail" alt="Profile picture" src="imageUser${correctUser.id}"
                                     width="200" height="200" />
                            </div>
                            <form  method="POST" action="/updateUser/photo" enctype="multipart/form-data" >
                                <input type="file" name="file" accept=".png, .jpg, .jpeg">
                                <input type="submit" value="CHANGE IMAGE">
                                <form:errors path="file" cssClass="error" >
                                    <spring:message text="file dont load"></spring:message>
                                </form:errors>
                            </form>
                            <hr>
                            <a href="#" class="button special fit"><span class="glyphicon glyphicon-send"></span> Send Message</a>
                        </div>
                        <div class="col-sm-8 col-md-8" text-center>
                            <h3><i class="fa fa-envelope"></i> Email : ${correctUser.email}</h3>
                            <hr>
                            <h4><i class="fa fa-calendar"></i> Date of Birth : ${correctUser.dateBirth}</h4>
                            <h4><i class="fa fa-mobile"></i> Phone : ${correctUser.phoneNumber}</h4>
                            <h4><i class="fa fa-map-marker"></i> Role : ${correctUser.role}</h4>
                            <h4><a href="#">Salary</a></h4>
                            <h4><a href="#">Schedule</a></h4>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
    <jsp:include page="layout/footer.jsp"/>
    </div><!-- /#main-wrapper -->
</body>
</html>

