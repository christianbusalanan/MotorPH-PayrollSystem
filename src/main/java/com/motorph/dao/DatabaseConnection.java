package com.motorph.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:MotorPH Payroll System.db";
    private static Connection connection = null;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("Connection to SQLite has been established.");
                
                // Test the connection by checking if leave_request table exists
                try {
                    var stmt = connection.createStatement();
                    var rs = stmt.executeQuery("SELECT COUNT(*) FROM leave_request");
                    if (rs.next()) {
                        System.out.println("Leave request table has " + rs.getInt(1) + " records");
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Warning: Could not access leave_request table: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection: " + e.getMessage());
            }
        }
    }
}