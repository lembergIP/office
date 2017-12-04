
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
<div id="main-wrapper" style="margin-bottom: 10%">
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
                        <h2><i class="fa fa-user"></i>${foundUser.firstName} ${foundUser.lastName}
                        <hr>
                        <div class="col-sm-4 col-md-4">
                            <div id="Photo">
                                <img id="picture" class="img-thumbnail" alt="Profile picture" src="imageUser${foundUser.id}" width="200" height="200" />
                            </div>
                            <hr>

                        </div>
                        <div class="col-sm-8 col-md-8" text-center>
                            <h3><i class="fa fa-envelope"></i> Email : ${foundUser.email}</h3>
                            <hr>
                            <h4><i class="fa fa-calendar"></i> Date of Birth : ${foundUser.dateBirth}</h4>
                            <h4><i class="fa fa-mobile"></i> Phone : ${foundUser.phoneNumber}</h4>
                            <h4><i class="fa fa-map-marker"></i> Role : ${foundUser.role}</h4>
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
