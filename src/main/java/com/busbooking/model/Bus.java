/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busbooking.model;

/**
 *
 * @author nine
 */

public class Bus {
    private int busId;
    private String busNumber;
    private String busName;
    private int capacity;
    private String busType;

    // Getters and Setters
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public String getBusName() { return busName; }
    public void setBusName(String busName) { this.busName = busName; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public String getBusType() { return busType; }
    public void setBusType(String busType) { this.busType = busType; }
}