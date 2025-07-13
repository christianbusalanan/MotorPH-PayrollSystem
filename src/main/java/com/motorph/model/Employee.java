package com.motorph.model;

import com.motorph.interfaces.Payable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee class extending Person and implementing Payable
 * Demonstrates Inheritance, Polymorphism, and Interface Implementation
 */
public class Employee extends Person implements Payable {
    protected String username;
    protected String status;
    protected String position;
    protected String department;
    protected String supervisor;
    protected double basicSalary;
    protected double hourlyRate;
    
    // Composition - Employee HAS-A list of leave requests
    protected List<LeaveRequest> leaveRequests;
    
    // Default constructor
    public Employee() {
        super();
        this.leaveRequests = new ArrayList<>();
    }
    
    // Parameterized constructor
    public Employee(String employeeId, String lastName, String firstName, String username, 
                   LocalDate birthday, String address, String phoneNumber, String status,
                   String position, String department, String supervisor, 
                   double basicSalary, double hourlyRate) {
        super(employeeId, firstName, lastName, birthday, address, phoneNumber);
        this.username = username;
        this.status = status;
        this.position = position;
        this.department = department;
        this.supervisor = supervisor;
        this.basicSalary = basicSalary;
        this.hourlyRate = hourlyRate;
        this.leaveRequests = new ArrayList<>();
    }
    
    // Polymorphism - Method Overriding from Person
    @Override
    public String getRole() {
        return "Employee";
    }
    
    @Override
    public String getDisplayInfo() {
        return String.format("%s - %s (%s)", getFullName(), position, department);
    }
    
    // Polymorphism - Method Overriding from Payable interface
    @Override
    public double calculateGrossPay(LocalDate startDate, LocalDate endDate) {
        // Default calculation - can be overridden by subclasses
        return hourlyRate * 160; // Assuming 160 hours per month
    }
    
    @Override
    public double calculateNetPay(LocalDate startDate, LocalDate endDate) {
        double grossPay = calculateGrossPay(startDate, endDate);
        double deductions = calculateDeductions(grossPay);
        double benefits = calculateBenefits();
        return grossPay + benefits - deductions;
    }
    
    // Polymorphism - Method Overloading
    public double calculateMonthlySalary() {
        return calculateGrossPay(null, null);
    }
    
    public double calculateMonthlySalary(int hoursWorked) {
        return hourlyRate * hoursWorked;
    }
    
    public double calculateMonthlySalary(int hoursWorked, double overtimeRate) {
        double regularPay = hourlyRate * Math.min(hoursWorked, 160);
        double overtimePay = (hoursWorked > 160) ? (hoursWorked - 160) * overtimeRate : 0;
        return regularPay + overtimePay;
    }
    
    // Protected methods for inheritance
    protected double calculateDeductions(double grossPay) {
        double sss = grossPay * 0.045; // 4.5% SSS
        double philHealth = grossPay * 0.015; // 1.5% PhilHealth
        double pagibig = grossPay * 0.02; // 2% Pag-IBIG
        return sss + philHealth + pagibig;
    }
    
    protected double calculateBenefits() {
        // Default benefits - can be overridden
        return 1500.0; // Rice subsidy
    }
    
    // Composition methods
    public void addLeaveRequest(LeaveRequest leaveRequest) {
        if (leaveRequest != null) {
            leaveRequests.add(leaveRequest);
        }
    }
    
    public List<LeaveRequest> getLeaveRequests() {
        return new ArrayList<>(leaveRequests); // Return copy for encapsulation
    }
    
    // Encapsulation - Getters and Setters with validation
    public String getEmployeeId() { return getId(); }
    public void setEmployeeId(String employeeId) { setId(employeeId); }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { 
        if (username != null && !username.trim().isEmpty()) {
            this.username = username.trim();
        }
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getSupervisor() { return supervisor; }
    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }
    
    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { 
        if (basicSalary >= 0) {
            this.basicSalary = basicSalary;
            // Automatically calculate hourly rate
            this.hourlyRate = basicSalary / 160;
        }
    }
    
    @Override
    public double getHourlyRate() { return hourlyRate; }
    
    @Override
    public void setHourlyRate(double hourlyRate) { 
        if (hourlyRate >= 0) {
            this.hourlyRate = hourlyRate;
        }
    }
    
    // Method to check if employee can take leave
    public boolean canTakeLeave(LocalDate startDate, LocalDate endDate) {
        // Business logic for leave validation
        return status.equals("Regular") && startDate.isBefore(endDate);
    }
}