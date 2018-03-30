<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users in Event</title>
    <!-- Google Web Font Embed -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,600,500,300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/templatemo_main.css">
</head>
<body style="background: url(resources/images/home/bg.jpg) no-repeat center center fixed;">
<div id="main-wrapper">
    <jsp:include page="layout/header.jsp"/>
<div class="row">
    <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-3 templatemo-content-wrapper">
        <div class="templatemo-content">

            <section id="home-text" class="active templatemo-content-section">
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
                        <td><a href="/mngs-deleteUser${user.id}FromEvent${eventID}"><button type="button" class="btn btn-danger" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></a></td>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="mngs-addUsersInEvent${eventID}" style="margin-left: 10%;margin-right: 10%">
                    <button type="button" class="btn btn-primary">Add More Users To Event</button></a>
                <a href="mngs-updateEvent${eventID}">  <button type="button" class="btn btn-warning">Update Event Info</button></a>
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
</div>
</body>
</html>
