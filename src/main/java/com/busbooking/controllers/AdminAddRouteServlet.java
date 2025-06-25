/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busbooking.controllers;

import com.busbooking.model.Route;
import com.busbooking.model.RouteDAO;
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
@WebServlet("/admin/addRoute")
public class AdminAddRouteServlet extends HttpServlet {

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

        String idParam = req.getParameter("routeId");
        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        double distance = Double.parseDouble(req.getParameter("distance"));

        Route r = new Route();
        r.setOrigin(origin);
        r.setDestination(destination);
        r.setDistance(distance);

        if (idParam != null && !idParam.isEmpty()) {
            // update existing
            r.setRouteId(Integer.parseInt(idParam));
            routeDAO.updateRoute(r);
        } else {
            // create new
            routeDAO.createRoute(r);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }

}
