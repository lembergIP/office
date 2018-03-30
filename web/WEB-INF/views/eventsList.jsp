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
    <script type="text/javascript" src="resources/js/timeValidator"></script>
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


            <div class="templatemo-content" >
                <section id="home-text" class="active templatemo-content-section">
                    <div style="margin-top: 2%;margin-left: 20%">
                        <form action="/eventsFindBetweenDates" method="post">
                            <p>   From: <input type="date" name="start" required="required">
                                To: <input type="date" name="finish" required="required">
                                <input type="submit" value="Find Events"></p>
                            <p><c:if test="${not empty error}">
                            <label><div class="error" style="text-align: right;">${error}</div></label>
                        </c:if></p>
                        </form>
                    </div>
                    <c:if test="${not empty flag}">
                        <a href="mngs-notConfirmedEvents" style="margin-right: 10%;float: right;margin-top: 1%"> <input type="button" value="SEE NOT CONFIRMED LIST"></a>
                    </c:if>
                    <table id="users-table" class="table table-bordered table-striped tablesorter">
                        <thead>
                        <tr>
                            <th>Name Events</th>
                            <th>Start event</th>
                            <th>End event</th>
                            <th>Direction</th>
                            <th>Description</th>
                            <th> Is confirmed</th>
                            <th>Users </th>
                            <th> </th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${events}" var="event" >
                        <tr>
                                <td>${event.type}</td>
                                <td>${event.timeFrom}</td>
                                <td>${event.timeEnd}</td>
                                <td>${event.direction} min</td>
                                <td>${event.description}</td>
                                <td><c:if test="${not empty flag}">${event.isConfirmed()}
                                    </c:if>
                                    <c:if test="${empty flag}">
                                    <a href="/mngOf-confirmEvent${event.id}"><button type="button">Confirm</button></a>
                                    </c:if>
                                </td>
                            <td><a href="usersInEvent${event.id}"><button type="button" class="btn btn-default" ><span class="glyphicon glyphicon-user" aria-hidden="true"></span></button></a></td>
                            <td><a href="/mngs-updateEvent${event.id}">
                                <span class="glyphicon glyphicon-cog" aria-hidden="true" ></span>
                            </a></td>
                            <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteEvent${event.id}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></td>
                                <!-- Modal delete-->
                                <div class="modal fade" id="deleteEvent${event.id}" role="dialog">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <h1>Are you sure you want to remove : <strong> ${event.type} start ${event.timeFrom}</strong>?</h1>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Back</button>
                                                <a href="mngs-deleteEvent${event.id}"><button type="button" class="btn btn-danger">Delete</button></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--end Modal delete-->
                        </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a href="/mngs-createEvent" style="float: right;margin-right: 5%">  <button type="button" class="btn btn-primary">Create Event</button></a>
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
</html>

