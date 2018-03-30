<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html >
<head>
    <meta charset="utf-8">
    <title>Events List</title>


    <meta name="viewport" content="width=device-width">

    <!-- Google Web Font Embed -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,600,500,300,700' rel='stylesheet' type='text/css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/templatemo_main.css">
    <script type="text/javascript" src="resources/js/jquery.tablesorter.js"></script>
    <script type="text/javascript" src="resources/js/timeValidator.js"></script>
    <%--<script>
        var dateNow= new Date();
    alert("date now : "+dateNow);
    </script>--%>
</head>
<body style="background: url(resources/images/home/bg.jpg) no-repeat center center fixed;">
<div id="main-wrapper">
    <jsp:include page="layout/header.jsp"/>
<div class="row">
    <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-3 templatemo-content-wrapper">
        <div class="templatemo-content">
            <section id="home-text" class="active templatemo-content-section">
                <form action="mngs-addUsersInEvent${eventID}" method="post" name="addUsersInEvent">
                <table id="users-table" class="table table-bordered table-striped tablesorter">
                    <thead>
                    <tr>
                        <th>Surname Name</th>
                        <th>Email</th>
                        <th>Role</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${users}" var="user" >
                    <tr>
                        <td><a href="/findUserById${user.id}"><p>${user.lastName} ${user.firstName}</p></a></td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td><div class="checkbox">
                            <input type="checkbox" name="userEmail" value="${user.email}" onclick="myFunction()">
                        </div></td>
                    </tr>
                        </c:forEach>


                    </tbody>
                </table>
                    <div style="margin-left: 20%">
                    <a href="mngs-updateEvent${eventID}">  <button type="button" class="btn btn-primary">Update Event Info</button></a>
                    <input type="submit" id="subBtn" value="Add Users In Event"  class="btn btn-danger" style="margin-left: 10%;margin-right: 10%" disabled>
                        <a href="usersInEvent${eventID}">  <button type="button" class="btn btn-success">Users in Event</button></a>
                    </div>
                </form>
            </section>
            <ul class="pagination pagination-lg">
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
            </ul>
        </div>
    </div>
</div>
    <jsp:include page="layout/footer.jsp"/>
</div><!-- /#main-wrapper -->
</div>

<script type="text/javascript" src="resources/js/eventAddUsers.js"></script>
</body>
</html>
