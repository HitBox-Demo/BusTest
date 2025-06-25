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

public class ScheduleDAO {
    public void createSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedules (bus_id, route_id, departure_date, departure_time, fare) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getBusId());
            stmt.setInt(2, schedule.getRouteId());
            stmt.setDate(3, schedule.getDepartureDate());
            stmt.setTime(4, schedule.getDepartureTime());
            stmt.setDouble(5, schedule.getFare());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Fetch all Schedule records */
    public List<Schedule> getAllSchedules() {
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT * FROM schedules";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Schedule sched = new Schedule();
                sched.setScheduleId(rs.getInt("schedule_id"));
                sched.setBusId(rs.getInt("bus_id"));
                sched.setRouteId(rs.getInt("route_id"));
                sched.setDepartureDate(rs.getDate("departure_date"));
                sched.setDepartureTime(rs.getTime("departure_time"));
                sched.setFare(rs.getDouble("fare"));
                list.add(sched);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Schedule getById(int scheduleId) {
        String sql = "SELECT * FROM schedules WHERE schedule_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Schedule sched = new Schedule();
                    sched.setScheduleId(rs.getInt("schedule_id"));
                    sched.setBusId(rs.getInt("bus_id"));
                    sched.setRouteId(rs.getInt("route_id"));
                    sched.setDepartureDate(rs.getDate("departure_date"));
                    sched.setDepartureTime(rs.getTime("departure_time"));
                    sched.setFare(rs.getDouble("fare"));
                    return sched;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateSchedule(Schedule s) {
        String sql = "UPDATE schedules SET bus_id=?, route_id=?, departure_date=?, departure_time=?, fare=? WHERE schedule_id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, s.getBusId());
            p.setInt(2, s.getRouteId());
            p.setDate(3, s.getDepartureDate());
            p.setTime(4, s.getDepartureTime());
            p.setDouble(5, s.getFare());
            p.setInt(6, s.getScheduleId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSchedule(int scheduleId) {
        String sql = "DELETE FROM schedules WHERE schedule_id = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, scheduleId);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}