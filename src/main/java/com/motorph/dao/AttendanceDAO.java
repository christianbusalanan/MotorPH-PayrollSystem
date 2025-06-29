package com.motorph.dao;

import com.motorph.model.Attendance;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    
    // SQLite stores dates and times as TEXT
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    public List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT * FROM attendance ORDER BY date DESC, employee_id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Attendance attendance = mapResultSetToAttendance(rs);
                if (attendance != null) {
                    attendanceList.add(attendance);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving attendance: " + e.getMessage());
        }
        
        return attendanceList;
    }
    
    public double getTotalHoursWorked(String employeeId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT SUM(time_out - time_in) as total_hours FROM attendance " +
                    "WHERE employee_id = ? AND date >= ? AND date <= ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeId);
            pstmt.setString(2, startDate.format(DATE_FORMATTER));
            pstmt.setString(3, endDate.format(DATE_FORMATTER));
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_hours");
            }
        } catch (SQLException e) {
            System.out.println("Error calculating total hours worked: " + e.getMessage());
        }
        
        return 0.0;
    }
    
    private Attendance mapResultSetToAttendance(ResultSet rs) {
        try {
            Attendance attendance = new Attendance();
            attendance.setEmployeeId(rs.getString("employee_id"));
            
            // Handle SQLite TEXT date field
            String dateStr = rs.getString("date");
            if (dateStr != null && !dateStr.trim().isEmpty()) {
                try {
                    LocalDate date = LocalDate.parse(dateStr.trim(), DATE_FORMATTER);
                    attendance.setDate(date);
                } catch (DateTimeParseException e) {
                    try {
                        LocalDate date = LocalDate.parse(dateStr.trim());
                        attendance.setDate(date);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Could not parse attendance date: " + dateStr);
                        return null;
                    }
                }
            }
            
            // Handle SQLite TEXT time fields
            String timeInStr = rs.getString("time_in");
            String timeOutStr = rs.getString("time_out");
            
            if (timeInStr != null && !timeInStr.trim().isEmpty()) {
                try {
                    LocalTime timeIn = LocalTime.parse(timeInStr.trim(), TIME_FORMATTER);
                    attendance.setTimeIn(timeIn);
                } catch (DateTimeParseException e) {
                    try {
                        LocalTime timeIn = LocalTime.parse(timeInStr.trim());
                        attendance.setTimeIn(timeIn);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Could not parse time in: " + timeInStr);
                    }
                }
            }
            
            if (timeOutStr != null && !timeOutStr.trim().isEmpty()) {
                try {
                    LocalTime timeOut = LocalTime.parse(timeOutStr.trim(), TIME_FORMATTER);
                    attendance.setTimeOut(timeOut);
                } catch (DateTimeParseException e) {
                    try {
                        LocalTime timeOut = LocalTime.parse(timeOutStr.trim());
                        attendance.setTimeOut(timeOut);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Could not parse time out: " + timeOutStr);
                    }
                }
            }
            
            return attendance;
        } catch (SQLException e) {
            System.out.println("Error mapping attendance result set: " + e.getMessage());
            return null;
        }
    }
}