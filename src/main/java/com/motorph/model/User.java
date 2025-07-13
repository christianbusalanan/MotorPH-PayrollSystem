package com.motorph.model;

import com.motorph.interfaces.Authenticatable;

/**
 * User class implementing Authenticatable interface
 * Demonstrates Encapsulation and Interface Implementation
 */
public class User implements Authenticatable {
    private String id;
    private String username;
    private String password;
    private String role;
    private boolean isLoggedIn;
    private int loginAttempts;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    
    // Composition - User HAS-A Employee (if applicable)
    private Employee employee;

    // Default constructor
    public User() {
        this.isLoggedIn = false;
        this.loginAttempts = 0;
    }

    // Constructor with all fields
    public User(String id, String username, String password, String role) {
        this();
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Implementation of Authenticatable interface
    @Override
    public boolean authenticate(String username, String password) {
        if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
            System.out.println("Account locked due to too many failed attempts");
            return false;
        }
        
        if (this.username.equals(username) && this.password.equals(password)) {
            isLoggedIn = true;
            loginAttempts = 0;
            return true;
        } else {
            loginAttempts++;
            return false;
        }
    }
    
    @Override
    public boolean isAuthorized(String action) {
        if (!isLoggedIn) return false;
        
        // Role-based authorization
        switch (role) {
            case "HR Manager":
                return true; // HR can do everything
            case "Payroll Staff":
                return action.contains("payroll") || action.contains("attendance");
            case "Employee":
                return action.contains("view") || action.contains("request");
            default:
                return false;
        }
    }
    
    @Override
    public void logout() {
        isLoggedIn = false;
        System.out.println("User " + username + " logged out");
    }
    
    // Polymorphism - Method Overloading
    public boolean hasRole(String role) {
        return this.role.equals(role);
    }
    
    public boolean hasRole(String... roles) {
        for (String r : roles) {
            if (this.role.equals(r)) return true;
        }
        return false;
    }

    // Encapsulation - Getters and Setters with validation
    public String getId() { return id; }
    public void setId(String id) { 
        if (id != null && !id.trim().isEmpty()) {
            this.id = id.trim();
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { 
        if (username != null && !username.trim().isEmpty()) {
            this.username = username.trim();
        }
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { 
        if (password != null && password.length() >= 6) {
            this.password = password;
        }
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public boolean isLoggedIn() { return isLoggedIn; }
    
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}