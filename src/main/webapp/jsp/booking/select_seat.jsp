<%-- 
    Document   : select_seat
    Created on : 10 Jun 2025, 18:48:12
    Author     : nine
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Select Your Seat</title>
        <!-- style.css must define .seat-map, .seat, .seat.booked, .seat.selected -->
        <link 
            href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" 
            rel="stylesheet"/>
        <link 
            href="${pageContext.request.contextPath}/assets/css/style.css" 
            rel="stylesheet"/>
    </head>
    <body>
    <!-- Navbar with Home, My Bookings, Logout -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">🚌️ GoExpress Bus Booking - Bus Booking System</a>
            
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
    <h2 class="text-center my-4">Select Your Seat</h2>

    <form method="post" action="${pageContext.request.contextPath}/book">
        <input type="hidden" name="scheduleId" value="${scheduleId}" />
        <input type="hidden" id="selectedSeats" name="selectedSeats" value="" />

        <div class="card p-4 mb-4 shadow-sm">
            <div class="seat-map">
                <c:forEach var="i" begin="1" end="${totalSeats}">
                    <button type="button"
                            class="seat ${bookedSeats.contains(i) ? 'booked' : ''}"
                            data-seat="${i}"
                            ${bookedSeats.contains(i) ? 'disabled' : ''}>
                        ${i}
                    </button>
                </c:forEach>
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="d-flex justify-content-center gap-3">
            <button type="submit" class="btn btn-primary px-4">
                Book Selected Seats
            </button>

            <button type="button" class="btn btn-outline-danger px-4"
                    onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">
                Cancel
            </button>
        </div>
    </form>
</div>

        <!-- Always include this script *after* the seat-map div -->
        <script src="${pageContext.request.contextPath}/assets/js/seat_selection.js"></script>
    </body>
</html>
