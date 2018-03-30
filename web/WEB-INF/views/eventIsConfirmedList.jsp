<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html >
<head>
    <meta charset="utf-8">
    <title>Events List</title>


    <meta name="viewport" content="width=device-width">

    <link href='http://fonts.googleapis.com/css?family=Raleway:400,600,500,300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.tablesorter.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body style="background: url(resources/images/menuPage/bg.jpg) no-repeat center center fixed;">
<div id="main-wrapper">

    <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 col-lg-offset-3 col-md-offset-3 templatemo-content-wrapper">
            <div class="templatemo-content">
                <section id="home-text" class="active templatemo-content-section">
                    <table id="users-table" class="table table-bordered table-striped tablesorter">
                        <thead>
                        <tr>
                            <th>Name Event</th>
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
                                <td>${event.isConfirmed()}</td>
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
                    <a href="/mngs-createEvent">  <button type="button" class="btn btn-primary">Add Event</button></a>
                    <div style="border: 5px solid black">
                        <form action="/eventsFindBetweenDates" method="post">
                            <p>From: <input type="datetime-local" name="start" required="required"></p>
                            <p>To: <input type="datetime-local" name="finish" required="required"></p>
                            <p><c:if test="${not empty error}">
                                <label><div class="error" style="text-align: right;">${error}</div></label>
                            </c:if></p>
                            <input type="submit" value="Find Events">
                        </form>
                    </div>

                </section>
            </div>
        </div>
    </div>

</div><!-- /#main-wrapper -->



</div>

</body>
</html>

