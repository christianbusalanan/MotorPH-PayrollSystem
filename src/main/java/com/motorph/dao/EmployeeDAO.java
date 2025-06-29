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
        String sql = "SELECT * FROM employee";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Employee employee = mapResultSetToEmployee(rs);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employees: " + e.getMessage());
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
        String sql = "SELECT * FROM employee WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employee by username: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean createEmployee(Employee employee) {
        String sql = "INSERT INTO employee (employee_id, last_name, first_name, username, birthday, " +
                    "address, phone_number, status, position, department, supervisor, basic_salary, hourly_rate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getFirstName());
            pstmt.setString(4, employee.getUsername());
            
            // Convert LocalDate to String for SQLite
            String birthdayStr = employee.getBirthday() != null ? 
                employee.getBirthday().format(DATE_FORMATTER) : null;
            pstmt.setString(5, birthdayStr);
            
            pstmt.setString(6, employee.getAddress());
            pstmt.setString(7, employee.getPhoneNumber());
            pstmt.setString(8, employee.getStatus());
            pstmt.setString(9, employee.getPosition());
            pstmt.setString(10, employee.getDepartment());
            pstmt.setString(11, employee.getSupervisor());
            pstmt.setDouble(12, employee.getBasicSalary());
            pstmt.setDouble(13, employee.getHourlyRate());
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error creating employee: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET last_name = ?, first_name = ?, username = ?, birthday = ?, " +
                    "address = ?, phone_number = ?, status = ?, position = ?, department = ?, " +
                    "supervisor = ?, basic_salary = ?, hourly_rate = ? WHERE employee_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getLastName());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setString(3, employee.getUsername());
            
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
            pstmt.setString(13, employee.getEmployeeId());
            
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
    
    private Employee mapResultSetToEmployee(ResultSet rs) {
        try {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getString("employee_id"));
            employee.setLastName(rs.getString("last_name"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setUsername(rs.getString("username"));
            
            // Handle SQLite TEXT date field
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
                        System.out.println("Could not parse birthday: " + birthdayStr);
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
            return null;
        }
    }
}