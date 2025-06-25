<%-- 
    Document   : index
    Created on : 10 Jun 2025, 18:40:14
    Author     : nine
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="java.util.List,
         com.busbooking.model.ScheduleDAO,
         com.busbooking.model.BusDAO,
         com.busbooking.model.RouteDAO,
         com.busbooking.model.Schedule,
         com.busbooking.model.Bus,
         com.busbooking.model.Route" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // session check
    if (session == null || session.getAttribute("userId") == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp");
        return;
    }

    // load data
    ScheduleDAO scheduleDAO = new ScheduleDAO();
    BusDAO busDAO = new BusDAO();
    RouteDAO routeDAO = new RouteDAO();

    List<Schedule> schedules = scheduleDAO.getAllSchedules();
    List<Bus> buses = busDAO.getAllBuses();
    List<Route> routes = routeDAO.getAllRoutes();

    request.setAttribute("schedules", schedules);
    request.setAttribute("buses", buses);
    request.setAttribute("routes", routes);
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Bus Booking System</title>
        <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
        <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/assets/css/style.css"/>
    </head>
    <body>
    <!-- Navbar with Home, My Bookings, Logout -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">️🚌 GoExpress Bus Booking - Bus Booking System</a>
            
            <!-- Toggle button for mobile view -->
            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navMenu"
                    aria-controls="navMenu"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Collapsible nav content -->
            <div class="collapse navbar-collapse" id="navMenu">
                <ul class="navbar-nav ms-auto align-items-center">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/my_bookings.jsp">
                            My Bookings
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link logout-btn"
                           href="${pageContext.request.contextPath}/logout">
                            Logout
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>


        <div class="container">
            <h1 class="mb-4"><br>Available Buses</h1>
            <div class="row">
                <c:forEach var="sched" items="${schedules}">
                    <div class="col-md-6">
                        <div class="card shadow-sm">
                            <div class="card-header">
                                Schedule ${sched.scheduleId}
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">
                                    <c:forEach var="bus" items="${buses}">
                                        <c:if test="${bus.busId == sched.busId}">
                                            ${bus.busName}
                                        </c:if>
                                    </c:forEach>
                                </h5>
                                <p class="card-text mb-3">
                                    <strong>Route:</strong>
                                    <c:forEach var="route" items="${routes}">
                                        <c:if test="${route.routeId == sched.routeId}">
                                            ${route.origin} &rarr; ${route.destination}
                                        </c:if>
                                    </c:forEach>
                                    <br/>
                                    <strong>Departure:</strong> ${sched.departureDate} ${sched.departureTime}
                                    <br/>
                                    <strong>Fare:</strong> RM ${sched.fare}
                                </p>
                                <a href="${pageContext.request.contextPath}/selectSeat?scheduleId=${sched.scheduleId}"
                                   class="btn btn-primary">
                                    Book Now
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <script
            src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js">
        </script>
    </body>
</html>
