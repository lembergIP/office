<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create event</title>
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

            <div class="templatemo-content">

                <section id="home-text" class="active templatemo-content-section">
                    <div class="col-sm-12 col-md-12 col-lg-12" style="padding-left: 10%;padding-top: 5%">
                        <h2 style="text-align: center">Create Event : </h2>
                        <hr>
                        <form action="/mngs-createEvent" method="post">
                            <div style="float: left;margin-right: 10%" ><p>Time start: </p><input type="datetime-local" name="startTime" required="required" value="${event.timeFrom}"></div>
                            <div style="float: left;margin-right: 10%" > <p>Time end: </p><input type="datetime-local" name="endTime" required="required" value="${event.timeEnd}"></div>

                            <p style="float: none">Event type: </p>
                                <select id="type" name="type" required="required">
                                    <c:forEach items="${type}" var="type">
                                        <option value="${type}">${type}</option>
                                    </c:forEach>
                                </select>
                            </p>
                            <p><c:if test="${not empty error}">
                                <label><div class="error" style="color: red;">${error}</div></label>
                            </c:if></p>
                            <p style="float: none;margin-top: 5%;margin-bottom: 5%">description :
                                <input type="text" max="250" style="min-width: 70%" name="description">
                            </p>
                            <input type="submit" value="Create Event">
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </div>


    <jsp:include page="layout/footer.jsp"/>



</div>

</body>
</html>
