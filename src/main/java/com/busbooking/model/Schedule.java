/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busbooking.model;

/**
 *
 * @author nine
 */

import java.sql.Date;
import java.sql.Time;

public class Schedule {
    private int scheduleId;
    private int busId;
    private int routeId;
    private Date departureDate;
    private Time departureTime;
    private double fare;

    // Getters and Setters
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }
    public Date getDepartureDate() { return departureDate; }
    public void setDepartureDate(Date departureDate) { this.departureDate = departureDate; }
    public Time getDepartureTime() { return departureTime; }
    public void setDepartureTime(Time departureTime) { this.departureTime = departureTime; }
    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    public Object getBus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}