/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busbooking.controllers;

import com.busbooking.model.Bus;
import com.busbooking.model.BusDAO;
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
@WebServlet("/admin/addBus")
public class AdminAddBusServlet extends HttpServlet {
    private BusDAO busDAO = new BusDAO();

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
        String id = req.getParameter("busId");
        Bus bus = new Bus();
        bus.setBusNumber(req.getParameter("busNumber"));
        bus.setBusName(req.getParameter("busName"));
        bus.setCapacity(Integer.parseInt(req.getParameter("capacity")));
        bus.setBusType(req.getParameter("busType"));

        if (id != null && !id.isEmpty()) {
            bus.setBusId(Integer.parseInt(id));
            busDAO.updateBus(bus);
        } else {
            busDAO.createBus(bus);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }

}
