package com.motorph.dao;

import com.motorph.model.LeaveRequest;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {
    
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT * FROM leave_request";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                leaveRequests.add(leaveRequest);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving leave requests: " + e.getMessage());
        }
        
        return leaveRequests;
    }
    
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(String employeeId) {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT * FROM leave_request WHERE employee_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                leaveRequests.add(leaveRequest);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving leave requests for employee: " + e.getMessage());
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
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error creating leave request: " + e.getMessage());
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
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating leave request status: " + e.getMessage());
            return false;
        }
    }
    
    private LeaveRequest mapResultSetToLeaveRequest(ResultSet rs) throws SQLException {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(rs.getString("id"));
        leaveRequest.setEmployeeId(rs.getString("employee_id"));
        leaveRequest.setLeaveType(rs.getString("leave_type"));
        leaveRequest.setStartDate(LocalDate.parse(rs.getString("start_date")));
        leaveRequest.setEndDate(LocalDate.parse(rs.getString("end_date")));
        leaveRequest.setStatus(rs.getString("status"));
        
        return leaveRequest;
    }
}