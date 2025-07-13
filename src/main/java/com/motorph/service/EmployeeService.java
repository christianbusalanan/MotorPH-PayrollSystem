package com.motorph.service;

import com.motorph.dao.EmployeeDAO;
import com.motorph.dao.UserDAO;
import com.motorph.factory.EmployeeFactory;
import com.motorph.model.Employee;
import com.motorph.model.Manager;
import com.motorph.model.User;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee Service with comprehensive OOP implementation
 * Demonstrates Dependency Injection, Polymorphism, and SOLID principles
 */
public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final UserDAO userDAO;
    
    // Dependency Injection through constructor
    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
        this.userDAO = new UserDAO();
    }
    
    // Constructor for dependency injection (for testing)
    public EmployeeService(EmployeeDAO employeeDAO, UserDAO userDAO) {
        this.employeeDAO = employeeDAO;
        this.userDAO = userDAO;
    }
    
    // Polymorphism - methods work with Employee and its subclasses
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        return employees.stream()
                .map(this::enhanceEmployee) // Convert to appropriate subclass
                .collect(Collectors.toList());
    }
    
    public List<Employee> getAllEmployeesWithUserDetails() {
        return employeeDAO.getAllEmployeesWithUserDetails();
    }
    
    public Employee getEmployeeById(String employeeId) {
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        return enhanceEmployee(employee);
    }
    
    public Employee getEmployeeByUsername(String username) {
        System.out.println("EmployeeService: Looking up employee by username: " + username);
        
        Employee employee = employeeDAO.getEmployeeWithUserDetails(username);
        
        if (employee == null) {
            System.out.println("EmployeeService: No employee found for username: " + username);
        } else {
            System.out.println("EmployeeService: Found employee: " + employee.getId() + " - " + employee.getFullName());
            employee = enhanceEmployee(employee);
        }
        
        return employee;
    }
    
    // Factory pattern usage
    public boolean createEmployee(Employee employee, String password, String role) {
        System.out.println("EmployeeService: Creating employee with OOP principles");
        System.out.println("EmployeeService: Employee ID: " + employee.getId());
        System.out.println("EmployeeService: Username: " + employee.getUsername());
        System.out.println("EmployeeService: Role: " + role);
        
        try {
            // Use factory to create appropriate employee type
            Employee enhancedEmployee = EmployeeFactory.createEmployee(
                employee.getId(), employee.getLastName(), employee.getFirstName(),
                employee.getUsername(), employee.getBirthday(), employee.getAddress(),
                employee.getPhoneNumber(), employee.getStatus(), employee.getPosition(),
                employee.getDepartment(), employee.getSupervisor(), 
                employee.getBasicSalary(), employee.getHourlyRate()
            );
            
            // Validate using polymorphism
            if (!isValidEmployee(enhancedEmployee)) {
                System.out.println("EmployeeService: Employee validation failed");
                return false;
            }
            
            // Create user account first (normalized database structure)
            User user = new User(enhancedEmployee.getId(), enhancedEmployee.getUsername(), password, role);
            System.out.println("EmployeeService: Creating user account...");
            boolean userCreated = userDAO.createUser(user);
            
            if (!userCreated) {
                System.out.println("EmployeeService: Failed to create user account");
                return false;
            }
            System.out.println("EmployeeService: User account created successfully");
            
            // Create employee record
            System.out.println("EmployeeService: Creating employee record...");
            boolean employeeCreated = employeeDAO.createEmployee(enhancedEmployee);
            
            if (!employeeCreated) {
                System.out.println("EmployeeService: Failed to create employee record, rolling back user creation");
                userDAO.deleteUser(enhancedEmployee.getId());
                return false;
            }
            
            System.out.println("EmployeeService: Employee created successfully with OOP principles");
            return true;
            
        } catch (Exception e) {
            System.out.println("EmployeeService: Error creating employee: " + e.getMessage());
            e.printStackTrace();
            
            // Cleanup in case of error
            try {
                userDAO.deleteUser(employee.getId());
                employeeDAO.deleteEmployee(employee.getId());
            } catch (Exception cleanupException) {
                System.out.println("EmployeeService: Error during cleanup: " + cleanupException.getMessage());
            }
            
            return false;
        }
    }
    
    public boolean updateEmployee(Employee employee) {
        // Validate using polymorphism
        if (!isValidEmployee(employee)) {
            return false;
        }
        
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
    
    // Polymorphism - method works with Employee and Manager
    public List<Manager> getAllManagers() {
        return getAllEmployees().stream()
                .filter(emp -> emp instanceof Manager)
                .map(emp -> (Manager) emp)
                .collect(Collectors.toList());
    }
    
    public List<Employee> getSubordinates(String managerId) {
        Employee manager = getEmployeeById(managerId);
        if (manager instanceof Manager) {
            return ((Manager) manager).getSubordinates();
        }
        return List.of();
    }
    
    // Validation using polymorphism
    private boolean isValidEmployee(Employee employee) {
        if (employee == null) return false;
        if (employee.getId() == null || employee.getId().trim().isEmpty()) return false;
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) return false;
        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) return false;
        if (employee.getBasicSalary() < 0) return false;
        if (employee.getHourlyRate() < 0) return false;
        
        // Additional validation for managers
        if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            if (manager.getManagementBonus() < 0) return false;
        }
        
        return true;
    }
    
    // Factory pattern helper - enhance employee to appropriate subclass
    private Employee enhanceEmployee(Employee employee) {
        if (employee == null) return null;
        
        // If already enhanced, return as is
        if (employee instanceof Manager) return employee;
        
        // Check if should be manager based on position
        if (isManagerialPosition(employee.getPosition())) {
            // Create manager instance with same data
            Manager manager = new Manager(
                employee.getId(), employee.getLastName(), employee.getFirstName(),
                employee.getUsername(), employee.getBirthday(), employee.getAddress(),
                employee.getPhoneNumber(), employee.getStatus(), employee.getPosition(),
                employee.getDepartment(), employee.getSupervisor(),
                employee.getBasicSalary(), employee.getHourlyRate()
            );
            return manager;
        }
        
        return employee;
    }
    
    private boolean isManagerialPosition(String position) {
        if (position == null) return false;
        String pos = position.toLowerCase();
        return pos.contains("manager") || pos.contains("head") || 
               pos.contains("chief") || pos.contains("director") ||
               pos.contains("supervisor") || pos.contains("team leader");
    }
    
    public String getEmployeeFullName(String employeeId) {
        Employee employee = getEmployeeById(employeeId);
        return employee != null ? employee.getFullName() : null;
    }
    
    // Polymorphism demonstration
    public void printEmployeeInfo(Employee employee) {
        System.out.println("Employee Information:");
        System.out.println("Role: " + employee.getRole()); // Calls overridden method
        System.out.println("Display Info: " + employee.getDisplayInfo()); // Calls overridden method
        System.out.println("Monthly Salary: " + employee.calculateMonthlySalary()); // Calls overridden method
        
        // Specific behavior for managers
        if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            System.out.println("Team Size: " + manager.getTeamSize());
            System.out.println("Management Bonus: " + manager.getManagementBonus());
        }
    }
}