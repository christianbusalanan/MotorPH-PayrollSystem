package com.motorph.interfaces;

import com.motorph.model.Employee;
import java.util.List;

/**
 * Interface for management capabilities
 * Demonstrates Interface Segregation Principle
 */
public interface Manageable {
    boolean approveLeaveRequest(String leaveRequestId);
    boolean rejectLeaveRequest(String leaveRequestId);
    List<Employee> getSubordinates();
    boolean canManage(Employee employee);
}