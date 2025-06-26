package com.busbooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Central place to obtain JDBC connections.
 *
 * ──────────────────────────────────────────────
 * • Works on Render (free or paid)  
 * • Reads DB settings from env-vars but provides
 *   safe fall-backs so it also runs on your laptop
 *   without extra config.  
 * • Requires mysql-connector-java ≥ 8.0.33
 * ──────────────────────────────────────────────
 *
 * Set these in Render → Environment:
 *   DB_HOST      turntable.proxy.rlwy.net
 *   DB_PORT      53235
 *   DB_NAME      railway
 *   DB_USER      root
 *   DB_PASSWORD  fEFYxsacOJqexzvVAFnLNvUdOHzrxRCp
 */
public final class DBConnection {

    /* 1) Load MySQL JDBC driver exactly once */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   // MySQL 8/9 driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    /* 2) Read env-vars with sensible fall-backs     */
    private static final String HOST = getenv("DB_HOST", "turntable.proxy.rlwy.net");
    private static final String PORT = getenv("DB_PORT", "53235");
    private static final String NAME = getenv("DB_NAME", "railway");
    private static final String USER = getenv("DB_USER", "root");
    private static final String PASS = getenv("DB_PASSWORD", "fEFYxsacOJqexzvVAFnLNvUdOHzrxRCp");

    /* 3) Build a modern JDBC URL                    */
    private static final String JDBC_URL = String.format(
        "jdbc:mysql://%s:%s/%s"
      + "?useSSL=false"
      + "&allowPublicKeyRetrieval=true"
      + "&serverTimezone=UTC",
        HOST, PORT, NAME
    );

    /** Get a live connection; remember to conn.close() */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASS);
    }

    /* ── helpers ─────────────────────────────────── */
    private static String getenv(String key, String fallback) {
        String v = System.getenv(key);
        return (v == null || v.isEmpty()) ? fallback : v;
    }
}
