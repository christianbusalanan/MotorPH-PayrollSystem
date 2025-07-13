package com.motorph.factory;

import com.motorph.model.Employee;
import com.motorph.model.Manager;
import java.time.LocalDate;

/**
 * Factory class for creating Employee objects
 * Demonstrates Factory Design Pattern and Polymorphism
 */
public class EmployeeFactory {
    
    // Factory method - returns Employee or Manager based on position
    public static Employee createEmployee(String employeeId, String lastName, String firstName, 
                                        String username, LocalDate birthday, String address, 
                                        String phoneNumber, String status, String position, 
                                        String department, String supervisor, 
                                        double basicSalary, double hourlyRate) {
        
        // Determine if position is managerial
        if (isManagerialPosition(position)) {
            return new Manager(employeeId, lastName, firstName, username, birthday, 
                             address, phoneNumber, status, position, department, 
                             supervisor, basicSalary, hourlyRate);
        } else {
            return new Employee(employeeId, lastName, firstName, username, birthday, 
                              address, phoneNumber, status, position, department, 
                              supervisor, basicSalary, hourlyRate);
        }
    }
    
    // Polymorphism - Method Overloading
    public static Employee createEmployee(String position) {
        if (isManagerialPosition(position)) {
            return new Manager();
        } else {
            return new Employee();
        }
    }
    
    public static Employee createEmployee(String employeeId, String position, double basicSalary) {
        Employee employee = createEmployee(position);
        employee.setId(employeeId);
        employee.setPosition(position);
        employee.setBasicSalary(basicSalary);
        return employee;
    }
    
    // Helper method to determine if position is managerial
    private static boolean isManagerialPosition(String position) {
        if (position == null) return false;
        
        String pos = position.toLowerCase();
        return pos.contains("manager") || 
               pos.contains("head") || 
               pos.contains("chief") || 
               pos.contains("director") ||
               pos.contains("supervisor") ||
               pos.contains("team leader");
    }
    
    // Method to create employee with default values
    public static Employee createDefaultEmployee(String employeeId, String position) {
        Employee employee = createEmployee(position);
        employee.setId(employeeId);
        employee.setPosition(position);
        employee.setStatus("Probationary");
        employee.setBasicSalary(25000.0); // Default salary
        return employee;
    }
}