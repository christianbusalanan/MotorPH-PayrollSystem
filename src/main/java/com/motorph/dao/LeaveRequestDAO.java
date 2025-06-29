package com.motorph.dao;

import com.motorph.model.LeaveRequest;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {
    
    // SQLite stores dates as TEXT in YYYY-MM-DD format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT * FROM leave_request ORDER BY start_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("Executing query: " + sql);
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                if (leaveRequest != null) {
                    leaveRequests.add(leaveRequest);
                    System.out.println("Mapped leave request: " + leaveRequest.getId());
                }
            }
            System.out.println("Retrieved " + leaveRequests.size() + " leave requests from database");
        } catch (SQLException e) {
            System.out.println("Error retrieving leave requests: " + e.getMessage());
            e.printStackTrace();
        }
        
        return leaveRequests;
    }
    
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(String employeeId) {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT * FROM leave_request WHERE employee_id = ? ORDER BY start_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeId);
            System.out.println("Executing query for employee: " + employeeId);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                if (leaveRequest != null) {
                    leaveRequests.add(leaveRequest);
                    System.out.println("Mapped leave request for employee " + employeeId + ": " + leaveRequest.getId());
                }
            }
            System.out.println("Retrieved " + leaveRequests.size() + " leave requests for employee: " + employeeId);
        } catch (SQLException e) {
            System.out.println("Error retrieving leave requests for employee: " + e.getMessage());
            e.printStackTrace();
        }
        
        return leaveRequests;
    }
    
    public boolean createLeaveRequest(LeaveRequest leaveRequest) {
        String sql = "INSERT INTO leave_request (id, employee_id, leave_type, start_date, end_date, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, leaveRequest.getId());
            pstmt.setString(2, leaveRequest.getEmployeeId());
            pstmt.setString(3, leaveRequest.getLeaveType());
            
            // Convert LocalDate to String for SQLite
            String startDateStr = leaveRequest.getStartDate().format(DATE_FORMATTER);
            String endDateStr = leaveRequest.getEndDate().format(DATE_FORMATTER);
            
            pstmt.setString(4, startDateStr);
            pstmt.setString(5, endDateStr);
            pstmt.setString(6, leaveRequest.getStatus());
            
            System.out.println("Creating leave request with dates: " + startDateStr + " to " + endDateStr);
            
            int rowsInserted = pstmt.executeUpdate();
            System.out.println("Leave request created: " + (rowsInserted > 0));
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error creating leave request: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateLeaveRequestStatus(String leaveRequestId, String status) {
        String sql = "UPDATE leave_request SET status = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setString(2, leaveRequestId);
            
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Leave request status updated: " + (rowsUpdated > 0));
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating leave request status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    private LeaveRequest mapResultSetToLeaveRequest(ResultSet rs) {
        try {
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setId(rs.getString("id"));
            leaveRequest.setEmployeeId(rs.getString("employee_id"));
            leaveRequest.setLeaveType(rs.getString("leave_type"));
            leaveRequest.setStatus(rs.getString("status"));
            
            // Handle SQLite TEXT date fields safely
            String startDateStr = rs.getString("start_date");
            String endDateStr = rs.getString("end_date");
            
            System.out.println("Processing dates - Start: '" + startDateStr + "', End: '" + endDateStr + "'");
            
            if (startDateStr != null && !startDateStr.trim().isEmpty()) {
                try {
                    // Try parsing as YYYY-MM-DD first (standard SQLite format)
                    LocalDate startDate = LocalDate.parse(startDateStr.trim(), DATE_FORMATTER);
                    leaveRequest.setStartDate(startDate);
                } catch (DateTimeParseException e1) {
                    try {
                        // Fallback: try parsing as ISO format (YYYY-MM-DD)
                        LocalDate startDate = LocalDate.parse(startDateStr.trim());
                        leaveRequest.setStartDate(startDate);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Could not parse start date: '" + startDateStr + "' - " + e2.getMessage());
                        return null; // Skip this record if date is invalid
                    }
                }
            } else {
                System.out.println("Start date is null or empty");
                return null;
            }
            
            if (endDateStr != null && !endDateStr.trim().isEmpty()) {
                try {
                    // Try parsing as YYYY-MM-DD first (standard SQLite format)
                    LocalDate endDate = LocalDate.parse(endDateStr.trim(), DATE_FORMATTER);
                    leaveRequest.setEndDate(endDate);
                } catch (DateTimeParseException e1) {
                    try {
                        // Fallback: try parsing as ISO format (YYYY-MM-DD)
                        LocalDate endDate = LocalDate.parse(endDateStr.trim());
                        leaveRequest.setEndDate(endDate);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Could not parse end date: '" + endDateStr + "' - " + e2.getMessage());
                        return null; // Skip this record if date is invalid
                    }
                }
            } else {
                System.out.println("End date is null or empty");
                return null;
            }
            
            System.out.println("Successfully mapped leave request: " + leaveRequest.getId() + 
                             " from " + leaveRequest.getStartDate() + " to " + leaveRequest.getEndDate());
            
            return leaveRequest;
        } catch (SQLException e) {
            System.out.println("Error mapping result set to leave request: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}