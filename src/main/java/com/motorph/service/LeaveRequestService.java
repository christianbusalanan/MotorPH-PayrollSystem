package com.motorph.service;

import com.motorph.dao.LeaveRequestDAO;
import com.motorph.model.LeaveRequest;
import java.time.LocalDate;
import java.util.List;

public class LeaveRequestService {
    private final LeaveRequestDAO leaveRequestDAO;
    
    public LeaveRequestService() {
        this.leaveRequestDAO = new LeaveRequestDAO();
    }
    
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestDAO.getAllLeaveRequests();
    }
    
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(String employeeId) {
        return leaveRequestDAO.getLeaveRequestsByEmployeeId(employeeId);
    }
    
    public boolean submitLeaveRequest(String employeeId, String leaveType, 
                                    LocalDate startDate, LocalDate endDate) {
        if (!isValidLeaveRequest(employeeId, leaveType, startDate, endDate)) {
            return false;
        }
        
        String leaveId = generateLeaveId(employeeId, startDate, endDate);
        LeaveRequest leaveRequest = new LeaveRequest(
            leaveId, employeeId, leaveType, startDate, endDate, "Pending"
        );
        
        return leaveRequestDAO.createLeaveRequest(leaveRequest);
    }
    
    public boolean updateLeaveRequestStatus(String leaveRequestId, String status) {
        if (!isValidStatus(status)) {
            return false;
        }
        
        return leaveRequestDAO.updateLeaveRequestStatus(leaveRequestId, status);
    }
    
    private boolean isValidLeaveRequest(String employeeId, String leaveType, 
                                      LocalDate startDate, LocalDate endDate) {
        return employeeId != null && !employeeId.trim().isEmpty() &&
               leaveType != null && !leaveType.trim().isEmpty() &&
               startDate != null && endDate != null &&
               !startDate.isAfter(endDate);
    }
    
    private boolean isValidStatus(String status) {
        return status != null && (status.equals("Pending") || 
                                 status.equals("Approved") || 
                                 status.equals("Rejected"));
    }
    
    private String generateLeaveId(String employeeId, LocalDate startDate, LocalDate endDate) {
        return employeeId + "-" + startDate + "-" + endDate;
    }
}