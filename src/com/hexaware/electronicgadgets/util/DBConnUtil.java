package com.hexaware.electronicgadgets.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = PropertyUtil.getPropertyString();
                if (url == null) {
                    throw new SQLException("Connection URL is null");
                }
                conn = DriverManager.getConnection(url);
                System.out.println("Connected to DB successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("DB connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing DB: " + e.getMessage());
        }
    }
}
