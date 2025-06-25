/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busbooking.controllers;

import com.busbooking.model.RouteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
/**
 *
 * @author nine
 */
@WebServlet("/admin/deleteRoute")
public class AdminDeleteRouteServlet extends HttpServlet {

    private final RouteDAO routeDAO = new RouteDAO();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("routeId"));
        routeDAO.deleteRoute(id);

        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }

}
