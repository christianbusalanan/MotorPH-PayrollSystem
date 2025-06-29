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
                
                // Test the connection and show database info
                testDatabaseConnection();
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    private static void testDatabaseConnection() {
        try {
            var stmt = connection.createStatement();
            
            // Check if leave_request table exists and show sample data
            try {
                var rs = stmt.executeQuery("SELECT COUNT(*) as count FROM leave_request");
                if (rs.next()) {
                    int count = rs.getInt("count");
                    System.out.println("Leave request table has " + count + " records");
                    
                    // Show sample data to understand the date format
                    if (count > 0) {
                        var sampleRs = stmt.executeQuery("SELECT id, employee_id, start_date, end_date, status FROM leave_request LIMIT 3");
                        System.out.println("Sample leave request data:");
                        while (sampleRs.next()) {
                            System.out.println("  ID: " + sampleRs.getString("id") + 
                                             ", Employee: " + sampleRs.getString("employee_id") +
                                             ", Start: '" + sampleRs.getString("start_date") + "'" +
                                             ", End: '" + sampleRs.getString("end_date") + "'" +
                                             ", Status: " + sampleRs.getString("status"));
                        }
                        sampleRs.close();
                    }
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println("Warning: Could not access leave_request table: " + e.getMessage());
            }
            
            // Check employee table
            try {
                var rs = stmt.executeQuery("SELECT COUNT(*) as count FROM employee");
                if (rs.next()) {
                    System.out.println("Employee table has " + rs.getInt("count") + " records");
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println("Warning: Could not access employee table: " + e.getMessage());
            }
            
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error testing database connection: " + e.getMessage());
        }
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