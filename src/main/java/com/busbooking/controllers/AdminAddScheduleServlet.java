/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busbooking.controllers;

import com.busbooking.model.Schedule;
import com.busbooking.model.ScheduleDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author nine
 */
@WebServlet("/admin/addSchedule")
public class AdminAddScheduleServlet extends HttpServlet {

    private final ScheduleDAO scheduleDAO = new ScheduleDAO();

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

        String idParam = req.getParameter("scheduleId");
        int busId = Integer.parseInt(req.getParameter("busId"));
        int routeId = Integer.parseInt(req.getParameter("routeId"));
        Date depDate = Date.valueOf(req.getParameter("departureDate"));       // yyyy-MM-dd
        Time depTime = Time.valueOf(req.getParameter("departureTime") + ":00"); // HH:mm -> +":00"
        double fare = Double.parseDouble(req.getParameter("fare"));

        Schedule s = new Schedule();
        s.setBusId(busId);
        s.setRouteId(routeId);
        s.setDepartureDate(depDate);
        s.setDepartureTime(depTime);
        s.setFare(fare);

        if (idParam != null && !idParam.isEmpty()) {
            // update
            s.setScheduleId(Integer.parseInt(idParam));
            scheduleDAO.updateSchedule(s);
        } else {
            // create
            scheduleDAO.createSchedule(s);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }

}
