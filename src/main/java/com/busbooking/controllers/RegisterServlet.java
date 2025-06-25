/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.busbooking.controllers;

import com.busbooking.model.User;
import com.busbooking.model.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author nine
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(password); // In production, hash the password
        user.setPhone(phone);
        user.setRole("user");

        UserDAO userDAO = new UserDAO();
        if (userDAO.emailExists(email)) {
            request.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            userDAO.createUser(user);
            response.sendRedirect(request.getContextPath() + "/jsp/user/login.jsp");
        }
    }

}
