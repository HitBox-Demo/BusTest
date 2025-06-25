<%-- 
    Document   : login
    Created on : 10 Jun 2025, 18:47:28
    Author     : nine
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login – Bus Booking System</title>
        <link rel="stylesheet" 
              href="${pageContext.request.contextPath}/assets/css/style.css">
        <link 
            href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" 
            rel="stylesheet"/>
    </head>
    <body class="login-page">        
        <div class="login-container">
            <div class="text-center mb-4">
                <h1 class="fw-bold">Welcome~'</h1>
                <br><h2 class="fw-semibold">Bus Booking System</h2></div>
            <div class="card shadow-sm login-card">
                <div class="text-center mb-3">
                    <img src="${pageContext.request.contextPath}/assets/img/icn.svg"
                         alt="Login Icon"
                         class="login-icon">
                <h3 class="card-title text-center mb-4">Login</h3>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input
                            type="email"
                            class="form-control"
                            id="email"
                            name="email"
                            placeholder="you@example.com"
                            required
                            autofocus>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input
                            type="password"
                            class="form-control"
                            id="password"
                            name="password"
                            placeholder="••••••••"
                            required>
                    </div>
                    <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                    <div class="text-center">
                        <small>Don't have an account?</small>
                        <a href="${pageContext.request.contextPath}/jsp/user/register.jsp">
                            Register
                        </a>
                    </div>
                </form>
            </div>
        </div>
                            
        <script 
            src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js">
        </script>
    </body>
</html>
