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
    
    public List<AttendanceWithEmployee> getAllAttendanceWithEmployeeDetails() {
        List<AttendanceWithEmployee> attendanceList = new ArrayList<>();
        String sql = """
            SELECT a.employee_id, e.last_name, e.first_name, a.date, a.time_in, a.time_out
            FROM attendance a
            INNER JOIN employee e ON a.employee_id = e.employee_id
            ORDER BY a.date DESC, a.employee_id
            """;
        
        System.out.println("AttendanceDAO: Executing query to get attendance with employee details");
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                AttendanceWithEmployee attendance = mapResultSetToAttendanceWithEmployee(rs);
                if (attendance != null) {
                    attendanceList.add(attendance);
                    System.out.println("AttendanceDAO: Mapped attendance for " + 
                                     attendance.getEmployeeId() + " - " + 
                                     attendance.getFirstName() + " " + attendance.getLastName() + 
                                     " on " + attendance.getDate());
                }
            }
            
            System.out.println("AttendanceDAO: Retrieved " + attendanceList.size() + " attendance records with employee details");
            
        } catch (SQLException e) {
            System.out.println("AttendanceDAO: Error retrieving attendance with employee details: " + e.getMessage());
            e.printStackTrace();
        }
        
        return attendanceList;
    }
    
    public List<AttendanceWithEmployee> getAttendanceWithEmployeeDetailsByEmployeeId(String employeeId) {
        List<AttendanceWithEmployee> attendanceList = new ArrayList<>();
        String sql = """
            SELECT a.employee_id, e.last_name, e.first_name, a.date, a.time_in, a.time_out
            FROM attendance a
            INNER JOIN employee e ON a.employee_id = e.employee_id
            WHERE a.employee_id = ?
            ORDER BY a.date DESC
            """;
        
        System.out.println("AttendanceDAO: Getting attendance with employee details for employee: " + employeeId);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                AttendanceWithEmployee attendance = mapResultSetToAttendanceWithEmployee(rs);
                if (attendance != null) {
                    attendanceList.add(attendance);
                }
            }
            
            System.out.println("AttendanceDAO: Retrieved " + attendanceList.size() + 
                             " attendance records for employee: " + employeeId);
            
        } catch (SQLException e) {
            System.out.println("AttendanceDAO: Error retrieving attendance for employee: " + e.getMessage());
            e.printStackTrace();
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
    
    private AttendanceWithEmployee mapResultSetToAttendanceWithEmployee(ResultSet rs) {
        try {
            AttendanceWithEmployee attendance = new AttendanceWithEmployee();
            attendance.setEmployeeId(rs.getString("employee_id"));
            attendance.setLastName(rs.getString("last_name"));
            attendance.setFirstName(rs.getString("first_name"));
            
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
            System.out.println("Error mapping attendance with employee result set: " + e.getMessage());
            return null;
        }
    }
    
    // Inner class to hold attendance data with employee details
    public static class AttendanceWithEmployee {
        private String employeeId;
        private String lastName;
        private String firstName;
        private LocalDate date;
        private LocalTime timeIn;
        private LocalTime timeOut;
        
        // Getters and setters
        public String getEmployeeId() { return employeeId; }
        public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
        
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        
        public LocalTime getTimeIn() { return timeIn; }
        public void setTimeIn(LocalTime timeIn) { this.timeIn = timeIn; }
        
        public LocalTime getTimeOut() { return timeOut; }
        public void setTimeOut(LocalTime timeOut) { this.timeOut = timeOut; }
        
        public double getHoursWorked() {
            if (timeIn != null && timeOut != null) {
                return (double) java.time.Duration.between(timeIn, timeOut).toMinutes() / 60.0;
            }
            return 0.0;
        }
        
        public String getFullName() {
            return firstName + " " + lastName;
        }
    }
}