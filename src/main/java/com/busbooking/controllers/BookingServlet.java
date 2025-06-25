/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busbooking.controllers;

import com.busbooking.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * GET /selectSeat?scheduleId=123 → renders select_seat.jsp POST /book →
 * processes & saves selectedSeats
 */
@WebServlet(urlPatterns = {"/selectSeat", "/book"})
public class BookingServlet extends HttpServlet {

    private BookingDAO bookingDAO = new BookingDAO();
    private ScheduleDAO scheduleDAO = new ScheduleDAO();
    private BusDAO busDAO = new BusDAO();

    // — GET: show the seat-map
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Auth check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
            return;
        }

        // 2) Read correct parameter name: scheduleId
        String schedParam = req.getParameter("scheduleId");
        if (schedParam == null || schedParam.isBlank()) {
            // 400 if someone tries to come in without it
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing scheduleId");
            return;
        }
        int scheduleId = Integer.parseInt(schedParam);

        // 3) Load the Schedule → Bus → capacity
        Schedule sched = scheduleDAO.getById(scheduleId);
        if (sched == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Schedule not found");
            return;
        }
        Bus bus = busDAO.getById(sched.getBusId());
        if (bus == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Bus for this schedule not found");
            return;
        }
        int totalSeats = bus.getCapacity();

        // 4) Fetch already-booked seats
        List<Integer> bookedSeats
                = bookingDAO.getBookedSeatNumbersForSchedule(scheduleId);

        // 5) Forward with exactly the attributes the JSP expects
        req.setAttribute("scheduleId", scheduleId);
        req.setAttribute("totalSeats", totalSeats);
        req.setAttribute("bookedSeats", bookedSeats);
        req.getRequestDispatcher("/jsp/booking/select_seat.jsp")
                .forward(req, resp);
    }

    // — POST: save the selected seats
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Auth check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/login.jsp");
            return;
        }
        int userId = (Integer) session.getAttribute("userId");

        // 2) Which schedule:
        int scheduleId = Integer.parseInt(req.getParameter("scheduleId"));

        // 3) Which seats?  (comma-separated string)
        String raw = req.getParameter("selectedSeats");
        if (raw != null && !raw.isBlank()) {
            for (String s : raw.split(",")) {
                int seatNo = Integer.parseInt(s);
                // guard against double-booking
                if (!bookingDAO.isSeatBooked(scheduleId, seatNo)) {
                    Booking b = new Booking();
                    b.setUserId(userId);
                    b.setScheduleId(scheduleId);
                    b.setSeatNumber(seatNo);
                    bookingDAO.createBooking(b);
                }
            }
        }

        // 4) Done → redirect to My Bookings
        resp.sendRedirect(req.getContextPath() + "/my_bookings.jsp");
    }
}
