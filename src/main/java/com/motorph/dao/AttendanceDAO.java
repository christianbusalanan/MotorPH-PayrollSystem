package com.motorph.dao;

import com.motorph.model.Attendance;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    
    public List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT * FROM attendance";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Attendance attendance = mapResultSetToAttendance(rs);
                attendanceList.add(attendance);
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
            pstmt.setString(2, startDate.toString());
            pstmt.setString(3, endDate.toString());
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_hours");
            }
        } catch (SQLException e) {
            System.out.println("Error calculating total hours worked: " + e.getMessage());
        }
        
        return 0.0;
    }
    
    private Attendance mapResultSetToAttendance(ResultSet rs) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(rs.getString("employee_id"));
        attendance.setDate(LocalDate.parse(rs.getString("date")));
        
        String timeInStr = rs.getString("time_in");
        String timeOutStr = rs.getString("time_out");
        
        if (timeInStr != null) {
            attendance.setTimeIn(LocalTime.parse(timeInStr));
        }
        if (timeOutStr != null) {
            attendance.setTimeOut(LocalTime.parse(timeOutStr));
        }
        
        return attendance;
    }
}