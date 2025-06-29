package com.motorph.service;

import com.motorph.dao.UserDAO;
import com.motorph.model.User;

public class AuthenticationService {
    private final UserDAO userDAO;
    
    public AuthenticationService() {
        this.userDAO = new UserDAO();
    }
    
    public User authenticateUser(String username, String password) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            return null;
        }
        
        return userDAO.authenticateUser(username.trim(), password.trim());
    }
    
    public boolean isValidRole(String role) {
        return role != null && (role.equals("HR Manager") || 
                               role.equals("Employee") || 
                               role.equals("Payroll Staff"));
    }
}