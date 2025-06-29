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
        System.out.println("EmployeeService: Creating employee with normalized database structure");
        System.out.println("EmployeeService: Employee ID: " + employee.getEmployeeId());
        System.out.println("EmployeeService: Username: " + employee.getUsername());
        System.out.println("EmployeeService: Role: " + role);
        
        try {
            // Calculate hourly rate if not set
            if (employee.getHourlyRate() == 0 && employee.getBasicSalary() > 0) {
                double hourlyRate = employee.getBasicSalary() / 160; // Assuming 160 hours per month
                employee.setHourlyRate(Math.round(hourlyRate * 100.0) / 100.0);
                System.out.println("EmployeeService: Calculated hourly rate: " + employee.getHourlyRate());
            }
            
            // Step 1: Create user account first (normalized database structure)
            User user = new User(employee.getEmployeeId(), employee.getUsername(), password, role);
            System.out.println("EmployeeService: Creating user account...");
            boolean userCreated = userDAO.createUser(user);
            
            if (!userCreated) {
                System.out.println("EmployeeService: Failed to create user account");
                return false;
            }
            System.out.println("EmployeeService: User account created successfully");
            
            // Step 2: Create employee record (without username, as it's in user table)
            System.out.println("EmployeeService: Creating employee record...");
            boolean employeeCreated = employeeDAO.createEmployee(employee);
            
            if (!employeeCreated) {
                System.out.println("EmployeeService: Failed to create employee record, rolling back user creation");
                // Rollback user creation if employee creation fails
                userDAO.deleteUser(employee.getEmployeeId());
                return false;
            }
            
            System.out.println("EmployeeService: Employee created successfully with normalized structure");
            return true;
            
        } catch (Exception e) {
            System.out.println("EmployeeService: Error creating employee: " + e.getMessage());
            e.printStackTrace();
            
            // Attempt cleanup in case of error
            try {
                userDAO.deleteUser(employee.getEmployeeId());
                employeeDAO.deleteEmployee(employee.getEmployeeId());
            } catch (Exception cleanupException) {
                System.out.println("EmployeeService: Error during cleanup: " + cleanupException.getMessage());
            }
            
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
            System.out.println("EmployeeService: Deleting employee with normalized structure: " + employeeId);
            
            // Delete user account first (foreign key constraint)
            boolean userDeleted = userDAO.deleteUser(employeeId);
            System.out.println("EmployeeService: User account deleted: " + userDeleted);
            
            // Delete employee record
            boolean employeeDeleted = employeeDAO.deleteEmployee(employeeId);
            System.out.println("EmployeeService: Employee record deleted: " + employeeDeleted);
            
            return userDeleted && employeeDeleted;
        } catch (Exception e) {
            System.out.println("EmployeeService: Error deleting employee: " + e.getMessage());
            e.printStackTrace();
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