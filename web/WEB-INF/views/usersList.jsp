<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html >
<head>
    <meta charset="utf-8">
    <title>Users List</title>

    <%--<link href='http://fonts.googleapis.com/css?family=Raleway:400,600,500,300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.tablesorter.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>


    <!-- Google Web Font Embed -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,600,500,300,700' rel='stylesheet' type='text/css'>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <script type="text/javascript" src="resources/js/jquery-3.2.1.js"></script>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/templatemo_main.css">
    <script type="text/javascript" src="resources/js/jquery.tablesorter.js"></script>


</head>
<body style="background: url(resources/images/home/bg.jpg) no-repeat center center fixed;">
<div id="main-wrapper">
    <jsp:include page="layout/header.jsp"/>

    <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-3 templatemo-content-wrapper">
            <div>
                Find user by email:
                <form action="findUserByEmail">
                    <input type="search" id="findUserByEmail" name="findUserByEmail">
                    <input type="submit" value="Search">
                </form>
            </div>
            <div class="templatemo-content">
                <section id="home-text" class="active templatemo-content-section">
                    <table id="users-table" class="table table-bordered table-striped tablesorter">
                        <thead>
                        <tr>
                            <th>Surname Name</th>
                            <th>Email</th>
                            <th>Birth Date</th>
                            <th>Role</th>
                            <th> </th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${usersList}" var="user" >
                            <tr>
                                <td><a href="/findUserById${user.id}"><p>${user.lastName} ${user.firstName}</p></a></td>
                                <td>${user.email}</td>
                                <td>${user.dateBirth}</td>
                                <td>${user.role}</td>
                                <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteUser${user.id}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                               <!-- Modal delete-->
                                <div class="modal fade" id="deleteUser${user.id}" role="dialog">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <h1>Are you sure you want to remove : <strong> ${user.lastName} ${user.firstName}</strong>?</h1>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Back</button>
                                                <a href="deleteUser${user.id}"><button type="button" class="btn btn-danger">Delete</button></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--end Modal delete-->


                            </td>
                        </c:forEach>
                        </tbody>
                    </table>
                    <a href="/adm-registration">  <button type="button" class="btn btn-primary">Add User</button></a>
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

</body>
<script type="text/javascript" src="resources/js/sortUsers.js"></script>
<script>$(document).ready(function()
    {
        $("#users-table").tablesorter();
    }
);
$(document).ready(function()
    {
        $("#users-table").tablesorter( {sortList: [[0,0], [1,0]]} );
    }
);</script>
</html>
