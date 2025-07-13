package com.motorph.model;

import com.motorph.interfaces.Manageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager class extending Employee and implementing Manageable
 * Demonstrates Inheritance, Polymorphism, and Multiple Interface Implementation
 */
public class Manager extends Employee implements Manageable {
    
    // Composition - Manager HAS-A list of subordinates
    private List<Employee> subordinates;
    private double managementBonus;
    
    // Default constructor
    public Manager() {
        super();
        this.subordinates = new ArrayList<>();
        this.managementBonus = 5000.0; // Default management bonus
    }
    
    // Parameterized constructor
    public Manager(String employeeId, String lastName, String firstName, String username, 
                   LocalDate birthday, String address, String phoneNumber, String status,
                   String position, String department, String supervisor, 
                   double basicSalary, double hourlyRate) {
        super(employeeId, lastName, firstName, username, birthday, address, phoneNumber, 
              status, position, department, supervisor, basicSalary, hourlyRate);
        this.subordinates = new ArrayList<>();
        this.managementBonus = 5000.0;
    }
    
    // Polymorphism - Method Overriding from Employee
    @Override
    public String getRole() {
        return "Manager";
    }
    
    @Override
    public String getDisplayInfo() {
        return String.format("%s - %s Manager (%s) - %d subordinates", 
                           getFullName(), position, department, subordinates.size());
    }
    
    // Polymorphism - Method Overriding for salary calculation
    @Override
    public double calculateGrossPay(LocalDate startDate, LocalDate endDate) {
        double basePay = super.calculateGrossPay(startDate, endDate);
        return basePay + managementBonus;
    }
    
    @Override
    protected double calculateBenefits() {
        double baseBenefits = super.calculateBenefits();
        double managerBenefits = 2000.0; // Additional manager benefits
        return baseBenefits + managerBenefits;
    }
    
    // Polymorphism - Method Overloading for manager-specific calculations
    public double calculateMonthlySalary(boolean includeBonus) {
        double baseSalary = super.calculateMonthlySalary();
        return includeBonus ? baseSalary + managementBonus : baseSalary;
    }
    
    // Implementation of Manageable interface
    @Override
    public boolean approveLeaveRequest(String leaveRequestId) {
        // Business logic for approving leave requests
        System.out.println("Manager " + getFullName() + " approved leave request: " + leaveRequestId);
        return true;
    }
    
    @Override
    public boolean rejectLeaveRequest(String leaveRequestId) {
        // Business logic for rejecting leave requests
        System.out.println("Manager " + getFullName() + " rejected leave request: " + leaveRequestId);
        return true;
    }
    
    @Override
    public List<Employee> getSubordinates() {
        return new ArrayList<>(subordinates); // Return copy for encapsulation
    }
    
    @Override
    public boolean canManage(Employee employee) {
        return employee != null && 
               employee.getDepartment().equals(this.getDepartment()) &&
               !employee.getId().equals(this.getId());
    }
    
    // Manager-specific methods
    public void addSubordinate(Employee employee) {
        if (employee != null && canManage(employee)) {
            subordinates.add(employee);
            employee.setSupervisor(this.getFullName());
        }
    }
    
    public void removeSubordinate(Employee employee) {
        subordinates.remove(employee);
    }
    
    public int getTeamSize() {
        return subordinates.size();
    }
    
    public double getTeamTotalSalary() {
        return subordinates.stream()
                .mapToDouble(emp -> emp.calculateMonthlySalary())
                .sum();
    }
    
    // Encapsulation
    public double getManagementBonus() { return managementBonus; }
    public void setManagementBonus(double managementBonus) { 
        if (managementBonus >= 0) {
            this.managementBonus = managementBonus;
        }
    }
}