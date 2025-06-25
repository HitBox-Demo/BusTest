/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busbooking.controllers;

import com.busbooking.model.BusDAO;
import com.busbooking.model.RouteDAO;
import com.busbooking.model.ScheduleDAO;
import com.busbooking.model.Bus;
import com.busbooking.model.Route;
import com.busbooking.model.Schedule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author nine
 */
@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private final BusDAO busDAO = new BusDAO();
    private final RouteDAO routeDAO = new RouteDAO();
    private final ScheduleDAO scheduleDAO = new ScheduleDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Auth check
        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
            return;
        }

        // 2) Load master lists
        List<Bus> buses = busDAO.getAllBuses();
        List<Route> routes = routeDAO.getAllRoutes();
        List<Schedule> schedules = scheduleDAO.getAllSchedules();

        req.setAttribute("buses", buses);
        req.setAttribute("routes", routes);
        req.setAttribute("schedules", schedules);

        // 3) Check for edit parameters and load the single entity
        String busIdParam = req.getParameter("busId");
        String routeIdParam = req.getParameter("routeId");
        String scheduleIdParam = req.getParameter("scheduleId");

        if (busIdParam != null && !busIdParam.isEmpty()) {
            int busId = Integer.parseInt(busIdParam);
            req.setAttribute("busToEdit", busDAO.getById(busId));
        }
        if (routeIdParam != null && !routeIdParam.isEmpty()) {
            int routeId = Integer.parseInt(routeIdParam);
            req.setAttribute("routeToEdit", routeDAO.getById(routeId));
        }
        if (scheduleIdParam != null && !scheduleIdParam.isEmpty()) {
            int scheduleId = Integer.parseInt(scheduleIdParam);
            req.setAttribute("schedToEdit", scheduleDAO.getById(scheduleId));
        }

        // 4) Forward to the JSP
        req.getRequestDispatcher("/jsp/admin/dashboard.jsp")
                .forward(req, resp);
    }
}
