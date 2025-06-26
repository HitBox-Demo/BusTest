package com.busbooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    /* 1) Load the MySQL JDBC driver once at class-load time */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   // MySQL 8 / 9 driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    /* 2) Connection details – taken from
          mysql://root:fEFYxsacOJqexzvVAFnLNvUdOHzrxRCp@turntable.proxy.rlwy.net:53235/railway
     */
    private static final String HOST     = "turntable.proxy.rlwy.net";
    private static final String PORT     = "53235";
    private static final String DATABASE = "railway";
    private static final String USER     = "root";
    private static final String PASSWORD = "fEFYxsacOJqexzvVAFnLNvUdOHzrxRCp";

    /* 3) Build the JDBC URL
          - useSSL=false   → avoids self-signed–cert warnings
          - allowPublicKeyRetrieval=true   → lets the driver fetch the SHA-256 auth key (MySQL 8+)
          - serverTimezone=UTC   → suppresses timezone warnings
     */
    private static final String JDBC_URL = String.format(
        "jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
        HOST, PORT, DATABASE
    );

    /** Obtain a live Connection (call -> conn.close() when finished) */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}