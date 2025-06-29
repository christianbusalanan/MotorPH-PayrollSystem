package com.motorph.service;

import com.motorph.dao.EmployeeDAO;
import com.motorph.dao.UserDAO;
import com.motorph.model.Employee;
import com.motorph.model.User;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final UserDAO userDAO;
    
    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
        this.userDAO = new UserDAO();
    }
    
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
    
    public List<Employee> getAllEmployeesWithUserDetails() {
        return employeeDAO.getAllEmployeesWithUserDetails();
    }
    
    public Employee getEmployeeById(String employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }
    
    public Employee getEmployeeByUsername(String username) {
        System.out.println("EmployeeService: Looking up employee by username: " + username);
        
        // First try to get employee with user details (includes username from user table)
        Employee employee = employeeDAO.getEmployeeWithUserDetails(username);
        
        if (employee == null) {
            System.out.println("EmployeeService: No employee found for username: " + username);
        } else {
            System.out.println("EmployeeService: Found employee: " + employee.getEmployeeId() + " - " + employee.getFullName());
        }
        
        return employee;
    }
    
    public Employee getEmployeeWithUserDetails(String username) {
        System.out.println("EmployeeService: Getting employee with user details for username: " + username);
        return employeeDAO.getEmployeeWithUserDetails(username);
    }
    
    public boolean createEmployee(Employee employee, String password, String role) {
        try {
            // Calculate hourly rate if not set
            if (employee.getHourlyRate() == 0 && employee.getBasicSalary() > 0) {
                double hourlyRate = employee.getBasicSalary() / 160; // Assuming 160 hours per month
                employee.setHourlyRate(Math.round(hourlyRate * 100.0) / 100.0);
            }
            
            // Create employee record
            boolean employeeCreated = employeeDAO.createEmployee(employee);
            
            if (employeeCreated) {
                // Create user account
                User user = new User(employee.getEmployeeId(), employee.getUsername(), password, role);
                boolean userCreated = userDAO.createUser(user);
                
                if (!userCreated) {
                    // Rollback employee creation if user creation fails
                    employeeDAO.deleteEmployee(employee.getEmployeeId());
                    return false;
                }
                
                return true;
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Error creating employee: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateEmployee(Employee employee) {
        // Recalculate hourly rate if basic salary changed
        if (employee.getBasicSalary() > 0) {
            double hourlyRate = employee.getBasicSalary() / 160;
            employee.setHourlyRate(Math.round(hourlyRate * 100.0) / 100.0);
        }
        
        return employeeDAO.updateEmployee(employee);
    }
    
    public boolean deleteEmployee(String employeeId) {
        try {
            // Delete user account first
            boolean userDeleted = userDAO.deleteUser(employeeId);
            
            // Delete employee record
            boolean employeeDeleted = employeeDAO.deleteEmployee(employeeId);
            
            return userDeleted && employeeDeleted;
        } catch (Exception e) {
            System.out.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
    
    public boolean isValidEmployeeId(String employeeId) {
        return employeeId != null && !employeeId.trim().isEmpty();
    }
    
    public String getEmployeeFullName(String employeeId) {
        Employee employee = getEmployeeById(employeeId);
        return employee != null ? employee.getFullName() : null;
    }
}