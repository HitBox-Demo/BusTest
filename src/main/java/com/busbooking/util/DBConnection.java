package com.busbooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
    String host = System.getenv("DB_HOST");
    String port = System.getenv("DB_PORT");
    String db = System.getenv("DB_NAME");
    String user = System.getenv("DB_USER");
    String pass = System.getenv("DB_PASSWORD");

    String url = "jdbc:mysql://" + host + ":" + port + "/" + db +
                 "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    return DriverManager.getConnection(url, user, pass);
}
}
