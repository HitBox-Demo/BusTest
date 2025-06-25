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

public class BusDAO {
    public void createBus(Bus bus) {
        String sql = "INSERT INTO buses (bus_number, bus_name, capacity, bus_type) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bus.getBusNumber());
            stmt.setString(2, bus.getBusName());
            stmt.setInt(3, bus.getCapacity());
            stmt.setString(4, bus.getBusType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bus> getAllBuses() {
        List<Bus> list = new ArrayList<>();
        String sql = "SELECT * FROM buses";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bus bus = new Bus();
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusNumber(rs.getString("bus_number"));
                bus.setBusName(rs.getString("bus_name"));
                bus.setCapacity(rs.getInt("capacity"));
                bus.setBusType(rs.getString("bus_type"));
                list.add(bus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Bus getById(int busId) {
        String sql = "SELECT * FROM buses WHERE bus_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, busId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bus bus = new Bus();
                    bus.setBusId(rs.getInt("bus_id"));
                    bus.setBusNumber(rs.getString("bus_number"));
                    bus.setBusName(rs.getString("bus_name"));
                    bus.setCapacity(rs.getInt("capacity"));
                    bus.setBusType(rs.getString("bus_type"));
                    return bus;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Update an existing bus
     */
    public boolean updateBus(Bus bus) {
        String sql = "UPDATE buses SET bus_number=?, bus_name=?, capacity=?, bus_type=? WHERE bus_id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, bus.getBusNumber());
            p.setString(2, bus.getBusName());
            p.setInt(3, bus.getCapacity());
            p.setString(4, bus.getBusType());
            p.setInt(5, bus.getBusId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Delete a bus by ID
     */
    public boolean deleteBus(int busId) {
        String sql = "DELETE FROM buses WHERE bus_id = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, busId);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}