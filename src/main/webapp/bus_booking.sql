/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  nine
 * Created: 10 Jun 2025
 */

-- Create user to connect to
CREATE USER 'bususer'@'localhost' IDENTIFIED BY 'bus1234';
GRANT ALL PRIVILEGES ON busbooking.* TO 'bususer'@'localhost';
FLUSH PRIVILEGES;

-- Create the database
CREATE DATABASE BusBooking;
USE BusBooking;

-- Users table
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    role ENUM('user', 'admin') DEFAULT 'user'
);

-- Buses table
CREATE TABLE Buses (
    bus_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_number VARCHAR(50) UNIQUE NOT NULL,
    bus_name VARCHAR(100),
    capacity INT NOT NULL,
    bus_type VARCHAR(50)
);

-- Routes table
CREATE TABLE Routes (
    route_id INT AUTO_INCREMENT PRIMARY KEY,
    origin VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    distance DECIMAL(10,2)
);

-- Schedules table
CREATE TABLE Schedules (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_id INT,
    route_id INT,
    departure_date DATE NOT NULL,
    departure_time TIME NOT NULL,
    fare DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (bus_id) REFERENCES Buses(bus_id),
    FOREIGN KEY (route_id) REFERENCES Routes(route_id)
);

-- Bookings table
CREATE TABLE Bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    schedule_id INT,
    user_id INT,
    seat_number INT NOT NULL,
    booking_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('active', 'canceled') DEFAULT 'active',
    FOREIGN KEY (schedule_id) REFERENCES Schedules(schedule_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Insert dummy data
INSERT INTO Users (name, email, password_hash, phone, role) VALUES
('Alice Tan', 'alice@example.com', 'password123', '0123456789', 'user'),
('Bob Lim', 'bob@example.com', 'password123', '0123456790', 'user'),
('Admin User', 'admin@example.com', 'admin123', '0123456791', 'admin');

INSERT INTO Buses (bus_number, bus_name, capacity, bus_type) VALUES
('BUS001', 'Express Coach', 40, 'express'),
('BUS002', 'Local Trans', 30, 'local');

INSERT INTO Routes (origin, destination, distance) VALUES
('Kuala Lumpur', 'Penang', 350.5),
('Penang', 'Johor Bahru', 650.0);

INSERT INTO Schedules (bus_id, route_id, departure_date, departure_time, fare) VALUES
(1, 1, '2024-12-01', '08:00:00', 50.00),
(2, 2, '2024-12-02', '09:00:00', 80.00);

INSERT INTO Bookings (schedule_id, user_id, seat_number, status) VALUES
(1, 1, 5, 'active'),
(1, 2, 6, 'active');