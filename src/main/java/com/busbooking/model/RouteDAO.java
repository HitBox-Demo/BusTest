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

public class RouteDAO {
    public void createRoute(Route route) {
        String sql = "INSERT INTO routes (origin, destination, distance) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, route.getOrigin());
            stmt.setString(2, route.getDestination());
            stmt.setDouble(3, route.getDistance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetch all Route records
     */
    public List<Route> getAllRoutes() {
        List<Route> list = new ArrayList<>();
        String sql = "SELECT * FROM routes";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Route route = new Route();
                route.setRouteId(rs.getInt("route_id"));
                route.setOrigin(rs.getString("origin"));
                route.setDestination(rs.getString("destination"));
                route.setDistance(rs.getDouble("distance"));
                list.add(route);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Route getById(int routeId) {
        String sql = "SELECT * FROM routes WHERE route_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, routeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Route r = new Route();
                    r.setRouteId(rs.getInt("route_id"));
                    r.setOrigin(rs.getString("origin"));
                    r.setDestination(rs.getString("destination"));
                    r.setDistance(rs.getDouble("distance"));
                    return r;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateRoute(Route r) {
        String sql = "UPDATE routes SET origin=?, destination=?, distance=? WHERE route_id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, r.getOrigin());
            p.setString(2, r.getDestination());
            p.setDouble(3, r.getDistance());
            p.setInt(4, r.getRouteId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRoute(int routeId) {
        String sql = "DELETE FROM routes WHERE route_id = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, routeId);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}