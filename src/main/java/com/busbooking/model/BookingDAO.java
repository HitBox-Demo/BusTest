/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busbooking.model;

/**
 *
 * @author nine
 */

import com.busbooking.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public void createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (schedule_id, user_id, seat_number) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getScheduleId());
            stmt.setInt(2, booking.getUserId());
            stmt.setInt(3, booking.getSeatNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isSeatBooked(int scheduleId, int seatNumber) {
        String sql = "SELECT COUNT(*) FROM bookings WHERE schedule_id = ? AND seat_number = ? AND status = 'active'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            stmt.setInt(2, seatNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ? AND status = 'active'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setScheduleId(rs.getInt("schedule_id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setSeatNumber(rs.getInt("seat_number"));
                booking.setBookingTimestamp(rs.getTimestamp("booking_timestamp"));
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    
    public List<Integer> findBookedSeatNumbers(int scheduleId) throws SQLException {
        String sql = "SELECT seat_number FROM bookings WHERE schedule_id = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, scheduleId);
            ResultSet rs = ps.executeQuery();
            List<Integer> seats = new ArrayList<>();
            while (rs.next()) {
                seats.add(rs.getInt("seat_number"));
            }
            return seats;
        }
    }
    
    public List<Integer> getBookedSeatNumbersForSchedule(int scheduleId) {
        List<Integer> seats = new ArrayList<>();
        String sql = "SELECT seat_number FROM bookings "
                + "WHERE schedule_id = ? AND status = 'active'";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    seats.add(rs.getInt("seat_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    public void cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status = 'canceled' WHERE booking_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}