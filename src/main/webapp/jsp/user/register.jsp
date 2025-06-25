<%-- 
    Document   : register
    Created on : 10 Jun 2025, 18:47:50
    Author     : nine
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Register – Bus Booking System</title>
        <link 
            href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" 
            rel="stylesheet"/>
        <link 
            href="${pageContext.request.contextPath}/assets/css/style.css" 
            rel="stylesheet"/>
    </head>
    <body class="login-page">        
        <div class="login-container">
            <div class="text-center mb-4">
            <!-- Page Header -->
            <div class="text-center mb-4">
                <h1 class="fw-bold">Bus Booking System</h1>
            </div>

            <div class="card shadow-sm login-card">
                <h3 class="card-title text-center mb-4">Register</h3>
                <form action="${pageContext.request.contextPath}/register" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input 
                            type="text" 
                            class="form-control" 
                            id="name" 
                            name="name" 
                            placeholder="John Doe" 
                            required 
                            autofocus>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input 
                            type="email" 
                            class="form-control" 
                            id="email" 
                            name="email" 
                            placeholder="you@example.com" 
                            required>
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
                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone</label>
                        <input 
                            type="tel" 
                            class="form-control" 
                            id="phone" 
                            name="phone" 
                            placeholder="012-3456789" 
                            required>
                    </div>
                    <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-primary">
                            Register
                        </button>
                    </div>
                    <div class="text-center">
                        <small>Already have an account?</small>
                        <a href="${pageContext.request.contextPath}/jsp/user/login.jsp">
                            Login
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
