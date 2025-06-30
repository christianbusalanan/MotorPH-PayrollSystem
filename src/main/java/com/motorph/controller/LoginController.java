package com.motorph.controller;

import com.motorph.model.User;
import com.motorph.service.AuthenticationService;
import com.motorph.gui.EmployeeDB;
import com.motorph.gui.HRManagerDB;
import com.motorph.gui.PayrollStaffDB;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginController {
    private final AuthenticationService authService;
    
    public LoginController() {
        this.authService = new AuthenticationService();
    }
    
    public void handleLogin(String username, String password, JFrame loginFrame) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, 
                "Username or Password cannot be empty.", 
                "Login Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user = authService.authenticateUser(username, password);
        
        if (user != null) {
            JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
            
            // Open appropriate dashboard based on role
            switch (user.getRole()) {
                case "HR Manager" -> new HRManagerDB().setVisible(true);
                case "Employee" -> new EmployeeDB(username).setVisible(true);
                case "Payroll Staff" -> new PayrollStaffDB().setVisible(true);
                default -> {
                    JOptionPane.showMessageDialog(loginFrame, 
                        "Unknown role: " + user.getRole(), 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            loginFrame.dispose(); // Close login form
        } else {
            JOptionPane.showMessageDialog(loginFrame, 
                "Invalid username or password. Please try again.", 
                "Login Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
