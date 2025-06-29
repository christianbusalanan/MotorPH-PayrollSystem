package com.motorph.dao;

import com.motorph.model.Employee;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    // SQLite stores dates as TEXT in YYYY-MM-DD format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee ORDER BY employee_id";
        
        System.out.println("EmployeeDAO: Executing query to get all employees");
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("EmployeeDAO: Query executed successfully");
            
            while (rs.next()) {
                Employee employee = mapResultSetToEmployee(rs);
                if (employee != null) {
                    employees.add(employee);
                    System.out.println("EmployeeDAO: Mapped employee: " + employee.getEmployeeId() + " - " + employee.getFullName());
                }
            }
            
            System.out.println("EmployeeDAO: Retrieved " + employees.size() + " employees total");
            
        } catch (SQLException e) {
            System.out.println("EmployeeDAO: Error retrieving employees: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employees;
    }
    
    public Employee getEmployeeById(String employeeId) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employee: " + e.getMessage());
        }
        
        return null;
    }
    
    public Employee getEmployeeByUsername(String username) {
        // Join user and employee tables to get employee data by username
        String sql = """
            SELECT e.* 
            FROM employee e 
            INNER JOIN user u ON e.employee_id = u.id 
            WHERE u.username = ?
            """;
        
        System.out.println("EmployeeDAO: Looking up employee by username: " + username);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Use the standard mapping method since we're only selecting from employee table
                Employee employee = mapResultSetToEmployee(rs);
                System.out.println("EmployeeDAO: Found employee for username '" + username + "': " + 
                                 (employee != null ? employee.getEmployeeId() : "null"));
                return employee;
            } else {
                System.out.println("EmployeeDAO: No employee found for username: " + username);
            }
        } catch (SQLException e) {
            System.out.println("EmployeeDAO: Error retrieving employee by username: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    public Employee getEmployeeWithUserDetails(String username) {
        // Get both employee and user details in one query
        String sql = """
            SELECT e.*, u.username, u.role 
            FROM employee e 
            INNER JOIN user u ON e.employee_id = u.id 
            WHERE u.username = ?
            """;
        
        System.out.println("EmployeeDAO: Getting employee with user details for username: " + username);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Use special mapping method for joined results
                Employee employee = mapResultSetToEmployeeWithUserDetails(rs);
                if (employee != null) {
                    System.out.println("EmployeeDAO: Successfully loaded employee with user details: " + 
                                     employee.getEmployeeId() + " (" + employee.getUsername() + ")");
                }
                return employee;
            } else {
                System.out.println("EmployeeDAO: No employee found with user details for username: " + username);
            }
        } catch (SQLException e) {
            System.out.println("EmployeeDAO: Error retrieving employee with user details: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<Employee> getAllEmployeesWithUserDetails() {
        List<Employee> employees = new ArrayList<>();
        // Join employee and user tables to get complete information
        String sql = """
            SELECT e.*, u.username, u.role 
            FROM employee e 
            LEFT JOIN user u ON e.employee_id = u.id 
            ORDER BY e.employee_id
            """;
        
        System.out.println("EmployeeDAO: Executing query to get all employees with user details");
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("EmployeeDAO: Query executed successfully");
            
            while (rs.next()) {
                // Use special mapping method for joined results
                Employee employee = mapResultSetToEmployeeWithUserDetails(rs);
                if (employee != null) {
                    employees.add(employee);
                    System.out.println("EmployeeDAO: Mapped employee with user details: " + 
                                     employee.getEmployeeId() + " - " + employee.getFullName() + 
                                     " (username: " + employee.getUsername() + ")");
                }
            }
            
            System.out.println("EmployeeDAO: Retrieved " + employees.size() + " employees with user details");
            
        } catch (SQLException e) {
            System.out.println("EmployeeDAO: Error retrieving employees with user details: " + e.getMessage());
            e.printStackTrace();
        }
        
        return employees;
    }
    
    public boolean createEmployee(Employee employee) {
        // For normalized database, we only store employee data (no username in employee table)
        String sql = "INSERT INTO employee (employee_id, last_name, first_name, birthday, " +
                    "address, phone_number, status, position, department, supervisor, basic_salary, hourly_rate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        System.out.println("EmployeeDAO: Creating employee record for ID: " + employee.getEmployeeId());
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getFirstName());
            
            // Convert LocalDate to String for SQLite
            String birthdayStr = employee.getBirthday() != null ? 
                employee.getBirthday().format(DATE_FORMATTER) : null;
            pstmt.setString(4, birthdayStr);
            
            pstmt.setString(5, employee.getAddress());
            pstmt.setString(6, employee.getPhoneNumber());
            pstmt.setString(7, employee.getStatus());
            pstmt.setString(8, employee.getPosition());
            pstmt.setString(9, employee.getDepartment());
            pstmt.setString(10, employee.getSupervisor());
            pstmt.setDouble(11, employee.getBasicSalary());
            pstmt.setDouble(12, employee.getHourlyRate());
            
            int rowsInserted = pstmt.executeUpdate();
            System.out.println("EmployeeDAO: Employee record created successfully: " + (rowsInserted > 0));
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("EmployeeDAO: Error creating employee: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateEmployee(Employee employee) {
        // For normalized database, we only update employee data (no username in employee table)
        String sql = "UPDATE employee SET last_name = ?, first_name = ?, birthday = ?, " +
                    "address = ?, phone_number = ?, status = ?, position = ?, department = ?, " +
                    "supervisor = ?, basic_salary = ?, hourly_rate = ? WHERE employee_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getLastName());
            pstmt.setString(2, employee.getFirstName());
            
            // Convert LocalDate to String for SQLite
            String birthdayStr = employee.getBirthday() != null ? 
                employee.getBirthday().format(DATE_FORMATTER) : null;
            pstmt.setString(3, birthdayStr);
            
            pstmt.setString(4, employee.getAddress());
            pstmt.setString(5, employee.getPhoneNumber());
            pstmt.setString(6, employee.getStatus());
            pstmt.setString(7, employee.getPosition());
            pstmt.setString(8, employee.getDepartment());
            pstmt.setString(9, employee.getSupervisor());
            pstmt.setDouble(10, employee.getBasicSalary());
            pstmt.setDouble(11, employee.getHourlyRate());
            pstmt.setString(12, employee.getEmployeeId());
            
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteEmployee(String employeeId) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
    
    // Standard mapping method for employee table only (no username field)
    private Employee mapResultSetToEmployee(ResultSet rs) {
        try {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getString("employee_id"));
            employee.setLastName(rs.getString("last_name"));
            employee.setFirstName(rs.getString("first_name"));
            
            // Handle SQLite TEXT date field safely
            String birthdayStr = rs.getString("birthday");
            if (birthdayStr != null && !birthdayStr.trim().isEmpty()) {
                try {
                    LocalDate birthday = LocalDate.parse(birthdayStr.trim(), DATE_FORMATTER);
                    employee.setBirthday(birthday);
                } catch (DateTimeParseException e) {
                    try {
                        LocalDate birthday = LocalDate.parse(birthdayStr.trim());
                        employee.setBirthday(birthday);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Could not parse birthday: " + birthdayStr + " for employee: " + employee.getEmployeeId());
                        // Continue without setting birthday
                    }
                }
            }
            
            employee.setAddress(rs.getString("address"));
            employee.setPhoneNumber(rs.getString("phone_number"));
            employee.setStatus(rs.getString("status"));
            employee.setPosition(rs.getString("position"));
            employee.setDepartment(rs.getString("department"));
            employee.setSupervisor(rs.getString("supervisor"));
            employee.setBasicSalary(rs.getDouble("basic_salary"));
            employee.setHourlyRate(rs.getDouble("hourly_rate"));
            
            return employee;
        } catch (SQLException e) {
            System.out.println("Error mapping employee result set: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Special mapping method for joined employee and user tables
    private Employee mapResultSetToEmployeeWithUserDetails(ResultSet rs) {
        try {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getString("employee_id"));
            employee.setLastName(rs.getString("last_name"));
            employee.setFirstName(rs.getString("first_name"));
            
            // Handle SQLite TEXT date field safely
            String birthdayStr = rs.getString("birthday");
            if (birthdayStr != null && !birthdayStr.trim().isEmpty()) {
                try {
                    LocalDate birthday = LocalDate.parse(birthdayStr.trim(), DATE_FORMATTER);
                    employee.setBirthday(birthday);
                } catch (DateTimeParseException e) {
                    try {
                        LocalDate birthday = LocalDate.parse(birthdayStr.trim());
                        employee.setBirthday(birthday);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Could not parse birthday: " + birthdayStr + " for employee: " + employee.getEmployeeId());
                        // Continue without setting birthday
                    }
                }
            }
            
            employee.setAddress(rs.getString("address"));
            employee.setPhoneNumber(rs.getString("phone_number"));
            employee.setStatus(rs.getString("status"));
            employee.setPosition(rs.getString("position"));
            employee.setDepartment(rs.getString("department"));
            employee.setSupervisor(rs.getString("supervisor"));
            employee.setBasicSalary(rs.getDouble("basic_salary"));
            employee.setHourlyRate(rs.getDouble("hourly_rate"));
            
            // Set username from user table (this column comes from the JOIN)
            try {
                String username = rs.getString("username");
                employee.setUsername(username);
            } catch (SQLException e) {
                // Username column might not exist in some queries, that's okay
                System.out.println("Username column not available in result set");
            }
            
            return employee;
        } catch (SQLException e) {
            System.out.println("Error mapping employee with user details result set: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}