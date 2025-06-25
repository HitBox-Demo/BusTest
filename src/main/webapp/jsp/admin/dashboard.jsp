<%-- 
    Document   : dashboard
    Created on : 10 Jun 2025, 18:48:31
    Author     : nine
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List,
         com.busbooking.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Auth & load lists (already in AdminDashboardServlet)
    // But since dashboard.jsp is forwarded from servlet,
    // those lists are in request attributes: buses, routes, schedules.
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet"/>
    </head>
    <body>
        <header class="main-header">
            <h1>🚌️ GoExpress Bus Booking</h1>
        </header>   
        <nav class="navbar navbar-dark bg-dark">
            <div class="container-fluid">
                <span class="navbar-brand mb-0 h1">Admin Dashboard</span>
                <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">Logout</a>
            </div>
        </nav>

        <div class="container">

            <div class="row gy-4">
                <!-- BUS FORM -->
                <div class="col-md-4">
                    <c:choose>
                        <c:when test="${not empty param.busId}">
                            <!-- Edit Bus -->
                            <div class="card shadow">
                                <div class="card-header bg-primary text-white">Edit Bus</div>
                                <div class="card-body">
                                    <form action="${pageContext.request.contextPath}/admin/addBus" method="post">
                                        <input type="hidden" name="busId" value="${param.busId}" />
                                        <input type="text" class="form-control mb-2" name="busNumber"
                                               placeholder="Bus Number" value="${busToEdit.busNumber}" required/>
                                        <input type="text" class="form-control mb-2" name="busName"
                                               placeholder="Bus Name" value="${busToEdit.busName}" required/>
                                        <input type="number" class="form-control mb-2" name="capacity"
                                               placeholder="Capacity" value="${busToEdit.capacity}" required/>
                                        <input type="text" class="form-control mb-2" name="busType"
                                               placeholder="Bus Type" value="${busToEdit.busType}" required/>
                                        <button type="submit" class="btn btn-primary w-100">Update Bus</button>
                                        <a href="${pageContext.request.contextPath}/admin/dashboard"
                                           class="btn btn-secondary w-100 mt-2">Cancel</a>
                                    </form>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- Add Bus -->
                            <div class="card shadow">
                                <div class="card-header bg-primary text-white">Add Bus</div>
                                <div class="card-body">
                                    <form action="${pageContext.request.contextPath}/admin/addBus" method="post">
                                        <input type="text" class="form-control mb-2" name="busNumber" placeholder="Bus Number" required/>
                                        <input type="text" class="form-control mb-2" name="busName"    placeholder="Bus Name" required/>
                                        <input type="number" class="form-control mb-2" name="capacity"   placeholder="Capacity" required/>
                                        <input type="text" class="form-control mb-2" name="busType"    placeholder="Bus Type" required/>
                                        <button type="submit" class="btn btn-primary w-100">Add Bus</button>
                                    </form>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- ROUTE FORM -->
                <div class="col-md-4">
                    <c:choose>
                        <c:when test="${not empty param.routeId}">
                            <div class="card shadow">
                                <div class="card-header bg-success text-white">Edit Route</div>
                                <div class="card-body">
                                    <form action="${pageContext.request.contextPath}/admin/addRoute" method="post">
                                        <input type="hidden" name="routeId" value="${param.routeId}" />
                                        <input type="text" class="form-control mb-2" name="origin"
                                               placeholder="Origin" value="${routeToEdit.origin}" required/>
                                        <input type="text" class="form-control mb-2" name="destination"
                                               placeholder="Destination" value="${routeToEdit.destination}" required/>
                                        <input type="number" class="form-control mb-2" name="distance"
                                               placeholder="Distance (km)" value="${routeToEdit.distance}" required/>
                                        <button type="submit" class="btn btn-success w-100">Update Route</button>
                                        <a href="${pageContext.request.contextPath}/admin/dashboard"
                                           class="btn btn-secondary w-100 mt-2">Cancel</a>
                                    </form>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="card shadow">
                                <div class="card-header bg-success text-white">Add Route</div>
                                <div class="card-body">
                                    <form action="${pageContext.request.contextPath}/admin/addRoute" method="post">
                                        <input type="text" class="form-control mb-2" name="origin"      placeholder="Origin" required/>
                                        <input type="text" class="form-control mb-2" name="destination" placeholder="Destination" required/>
                                        <input type="number" class="form-control mb-2" name="distance"    placeholder="Distance (km)" required/>
                                        <button type="submit" class="btn btn-success w-100">Add Route</button>
                                    </form>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- SCHEDULE FORM -->
                <div class="col-md-4">
                    <c:choose>
                        <c:when test="${not empty param.scheduleId}">
                            <div class="card shadow">
                                <div class="card-header bg-warning text-dark">Edit Schedule</div>
                                <div class="card-body">
                                    <form action="${pageContext.request.contextPath}/admin/addSchedule" method="post">
                                        <input type="hidden" name="scheduleId" value="${param.scheduleId}" />
                                        <select class="form-select mb-2" name="busId" required>
                                            <option value="" disabled>Select Bus</option>
                                            <c:forEach var="bus" items="${buses}">
                                                <option value="${bus.busId}"
                                                        ${bus.busId==schedToEdit.busId?'selected':''}>
                                                    ${bus.busNumber} – ${bus.busName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <select class="form-select mb-2" name="routeId" required>
                                            <option value="" disabled>Select Route</option>
                                            <c:forEach var="route" items="${routes}">
                                                <option value="${route.routeId}"
                                                        ${route.routeId==schedToEdit.routeId?'selected':''}>
                                                    ${route.origin} → ${route.destination}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <input type="date"  class="form-control mb-2" name="departureDate"
                                               value="${schedToEdit.departureDate}" required/>
                                        <input type="time"  class="form-control mb-2" name="departureTime"
                                               value="${schedToEdit.departureTime}" required/>
                                        <input type="number" class="form-control mb-2" name="fare"
                                               placeholder="Fare (RM)" value="${schedToEdit.fare}" required/>
                                        <button type="submit" class="btn btn-warning w-100">Update Schedule</button>
                                        <a href="${pageContext.request.contextPath}/admin/dashboard"
                                           class="btn btn-secondary w-100 mt-2">Cancel</a>
                                    </form>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="card shadow">
                                <div class="card-header bg-warning text-dark">Add Schedule</div>
                                <div class="card-body">
                                    <form action="${pageContext.request.contextPath}/admin/addSchedule" method="post">
                                        <select class="form-select mb-2" name="busId" required>
                                            <option disabled selected>Select Bus</option>
                                            <c:forEach var="bus" items="${buses}">
                                                <option value="${bus.busId}">
                                                    ${bus.busNumber} – ${bus.busName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <select class="form-select mb-2" name="routeId" required>
                                            <option disabled selected>Select Route</option>
                                            <c:forEach var="route" items="${routes}">
                                                <option value="${route.routeId}">
                                                    ${route.origin} → ${route.destination}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <input type="date" class="form-control mb-2" name="departureDate" required/>
                                        <input type="time" class="form-control mb-2" name="departureTime" required/>
                                        <input type="number" class="form-control mb-2" name="fare"
                                               placeholder="Fare (RM)" required/>
                                        <button type="submit" class="btn btn-warning w-100">Add Schedule</button>
                                    </form>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <!-- CURRENT LISTS -->
            <div class="section">
                <h4>Current Buses</h4>
                <table class="table table-striped table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th><th>Number</th><th>Name</th><th>Cap.</th><th>Type</th><th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="bus" items="${buses}">
                            <tr>
                                <td>${bus.busId}</td>
                                <td>${bus.busNumber}</td>
                                <td>${bus.busName}</td>
                                <td>${bus.capacity}</td>
                                <td>${bus.busType}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/dashboard?busId=${bus.busId}"
                                       class="btn btn-sm btn-secondary">Edit</a>
                                    <form action="${pageContext.request.contextPath}/admin/deleteBus" method="post"
                                          style="display:inline">
                                        <input type="hidden" name="busId" value="${bus.busId}"/>
                                        <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="section">
                <h4>Current Routes</h4>
                <table class="table table-striped table-bordered">
                    <thead class="table-dark">
                        <tr><th>ID</th><th>Origin</th><th>Destination</th><th>Distance</th><th>Action</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="route" items="${routes}">
                            <tr>
                                <td>${route.routeId}</td>
                                <td>${route.origin}</td>
                                <td>${route.destination}</td>
                                <td>${route.distance}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/dashboard?routeId=${route.routeId}"
                                       class="btn btn-sm btn-secondary">Edit</a>
                                    <form action="${pageContext.request.contextPath}/admin/deleteRoute" method="post"
                                          style="display:inline">
                                        <input type="hidden" name="routeId" value="${route.routeId}"/>
                                        <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="section mb-5">
                <h4>Current Schedules</h4>
                <table class="table table-striped table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th><th>Bus</th><th>Route</th><th>Depart</th><th>Fare</th><th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sched" items="${schedules}">
                            <tr>
                                <td>${sched.scheduleId}</td>
                                <td>
                                    <c:forEach var="b" items="${buses}">
                                        <c:if test="${b.busId==sched.busId}">${b.busNumber}</c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach var="r" items="${routes}">
                                        <c:if test="${r.routeId==sched.routeId}">
                                            ${r.origin} → ${r.destination}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${sched.departureDate} ${sched.departureTime}</td>
                                <td>${sched.fare}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/dashboard?scheduleId=${sched.scheduleId}"
                                       class="btn btn-sm btn-secondary">Edit</a>
                                    <form action="${pageContext.request.contextPath}/admin/deleteSchedule"
                                          method="post" style="display:inline">
                                        <input type="hidden" name="scheduleId" value="${sched.scheduleId}"/>
                                        <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div><!-- /container -->

        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
