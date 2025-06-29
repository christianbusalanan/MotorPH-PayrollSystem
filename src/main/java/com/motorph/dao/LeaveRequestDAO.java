package com.motorph.dao;

import com.motorph.model.LeaveRequest;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {
    
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT * FROM leave_request ORDER BY start_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                if (leaveRequest != null) {
                    leaveRequests.add(leaveRequest);
                }
            }
            System.out.println("Retrieved " + leaveRequests.size() + " leave requests");
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
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                if (leaveRequest != null) {
                    leaveRequests.add(leaveRequest);
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
            pstmt.setString(4, leaveRequest.getStartDate().toString());
            pstmt.setString(5, leaveRequest.getEndDate().toString());
            pstmt.setString(6, leaveRequest.getStatus());
            
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
            
            // Handle date parsing more safely
            String startDateStr = rs.getString("start_date");
            String endDateStr = rs.getString("end_date");
            
            if (startDateStr != null && !startDateStr.trim().isEmpty()) {
                try {
                    leaveRequest.setStartDate(LocalDate.parse(startDateStr));
                } catch (Exception e) {
                    System.out.println("Error parsing start date: " + startDateStr + " - " + e.getMessage());
                    return null;
                }
            }
            
            if (endDateStr != null && !endDateStr.trim().isEmpty()) {
                try {
                    leaveRequest.setEndDate(LocalDate.parse(endDateStr));
                } catch (Exception e) {
                    System.out.println("Error parsing end date: " + endDateStr + " - " + e.getMessage());
                    return null;
                }
            }
            
            leaveRequest.setStatus(rs.getString("status"));
            
            return leaveRequest;
        } catch (SQLException e) {
            System.out.println("Error mapping result set to leave request: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}