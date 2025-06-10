/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph.v4;

/**
 *
 * @author KrisChan
 */
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
public class Database {
    private static final String URL = "jdbc:sqlite:MotorPH Payroll System.db";
    private static Connection connection = null;

  
    public static Connection getConnection() {
    try {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        }
    } catch (SQLException e) {
        System.out.println("Failed to connect to the database: " + e.getMessage());
    }
    return connection;
}
    
    public static String verifyLogin(String username, String password) {
    String sql = "SELECT role FROM user WHERE username = ? AND password = ?";
    
    try (Connection conn = getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getString("role");
        }
    } catch (SQLException e) {
        System.out.println("Login verification failed: " + e.getMessage());
    }

    return null;
}
     
    public static ResultSet getAllEmployees() {
    String sql = "SELECT employee_id, last_name, first_name, birthday, address, phone_number, status, position, department, supervisor, basic_salary, hourly_rate FROM employee";
    try {
        Connection conn = getConnection(); // Ensure connection remains open
        if (conn == null || conn.isClosed()) {
            System.out.println("Database connection is closed. Reconnecting...");
            conn = DriverManager.getConnection(URL); // Reconnect
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery(); // Return the result set
    } catch (SQLException e) {
        System.out.println("Error retrieving employees: " + e.getMessage());
        return null;
    }
}
    
    public static ResultSet getAttendance() {
    String sql = "SELECT e.last_name, e.first_name, a.date, a.time_in, a.time_out " +
                 "FROM employee AS e JOIN attendance AS a " +
                 "ON e.employee_id = a.employee_id";
    try {
        Connection conn = getConnection();
        if (conn == null || conn.isClosed()) {
            System.out.println("Database connection is closed. Reconnecting...");
            conn = DriverManager.getConnection(URL);
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery();
    } catch (SQLException e) {
        System.out.println("Error retrieving attendance: " + e.getMessage());
        return null;
    }
}
    
    
    
    public static ResultSet getPayrollDetails() {
    String sql = "SELECT employee_id, period_start , period_end, working_hours, overtime_hours, sss_contribution, philhealth_contribution, pagibig_contribution, witholding_tax, rice_subsidy, phone_allowance, clothing_allowance FROM payroll";
    try {
        Connection conn = getConnection(); // Ensure connection remains open
        if (conn == null || conn.isClosed()) {
            System.out.println("Database connection is closed. Reconnecting...");
            conn = DriverManager.getConnection(URL); // Reconnect
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery(); 
    } catch (SQLException e) {
        System.out.println("Error retrieving employees: " + e.getMessage());
        return null;
    }
}
   
 
    
    public static float getHourlyRate(String employeeId) {
    String sql = "SELECT hourly_rate FROM employee WHERE employee_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, employeeId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getFloat("hourly_rate");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving hourly rate: " + e.getMessage());
    }
    return 0; 
}
    
    public static float getBasicSalary(String employeeId) {
    String sql = "SELECT basic_salary FROM employee WHERE employee_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, employeeId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getFloat("basic_salary");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving basic_salary: " + e.getMessage());
    }
    return 0; 
}
    
    
    public static boolean insertPayrollRecord(String payroll_id, String employeeId, String period_start, String period_end, int working_hours, 
                                          int overtime_hours, double sss_contribution, double philhealth_contribution, double pagibig_contribution, double witholding_tax, double rice_subsidy, double phone_allowance, double clothing_allowance) {
    String sql = "INSERT INTO payroll (payroll_id, employee_id, period_start , period_end, working_hours, overtime_hours, sss_contribution, philhealth_contribution, pagibig_contribution, witholding_tax, rice_subsidy, phone_allowance, clothing_allowance ) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, payroll_id);
        pstmt.setString(2, employeeId);
        pstmt.setString(3, period_start);
        pstmt.setString(4, period_end);
        pstmt.setInt(5, working_hours);
        pstmt.setInt(6, overtime_hours);
        pstmt.setDouble(7, sss_contribution);
        pstmt.setDouble(8, philhealth_contribution);
        pstmt.setDouble(9, pagibig_contribution);
        pstmt.setDouble(10, witholding_tax);
        pstmt.setDouble(11, rice_subsidy);
        pstmt.setDouble(12, phone_allowance);
        pstmt.setDouble(13, clothing_allowance);

        int rowsInserted = pstmt.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        System.out.println("Error inserting payroll record: " + e.getMessage());
        return false;
    }
}
    
    public static boolean createEmployee(String employee_id, String last_name, String first_name, String birthday,  
                                     String address, String phone_number, String status, String position, 
                                     String department, String supervisor, double basic_salary, double hourly_rate, String username, String password, 
                                     String role) {
    String sqlEmployee = "INSERT INTO employee (employee_id, last_name, first_name, username, birthday, address, phone_number, status, position, department, supervisor, basic_salary, hourly_rate) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String sqlUser = "INSERT INTO user (id, username, password, role) VALUES(?, ?, ?, ?)";

    try (Connection conn = getConnection()) {
        conn.setAutoCommit(false); 

       
        try (PreparedStatement pstmtEmp = conn.prepareStatement(sqlEmployee);
             PreparedStatement pstmtUser = conn.prepareStatement(sqlUser)) {

           
            pstmtEmp.setString(1, employee_id);
            pstmtEmp.setString(2, last_name);
            pstmtEmp.setString(3, first_name);
            pstmtEmp.setString(4, username);
            pstmtEmp.setString(5, birthday);
            pstmtEmp.setString(6, address);
            pstmtEmp.setString(7, phone_number);
            pstmtEmp.setString(8, status);
            pstmtEmp.setString(9, position);
            pstmtEmp.setString(10, department);
            pstmtEmp.setString(11, supervisor);
            pstmtEmp.setDouble(12, basic_salary);
            pstmtEmp.setDouble(13, hourly_rate);
            int empRowsInserted = pstmtEmp.executeUpdate();

            
            pstmtUser.setString(1, employee_id); 
            pstmtUser.setString(2, username);
            pstmtUser.setString(3, password);
            pstmtUser.setString(4, role);
            int userRowsInserted = pstmtUser.executeUpdate();

           
            if (empRowsInserted > 0 && userRowsInserted > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback(); 
                System.out.println("Transaction rolled back due to failure.");
                return false;
            }
        }
    } catch (SQLException e) {
        System.out.println("Error inserting employee and user: " + e.getMessage());
        return false;
    }
}

  
    
    public static Employee getEmployeeDetails(String username) {
    String sql = "SELECT * FROM employee where username = ?";;
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("Employee Username Found: " + username); 

            return new Employee(
                rs.getString("employee_id"),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("username"),
                rs.getString("birthday"),
                rs.getString("address"),
                rs.getString("phone_number"),
                rs.getString("status"),
                rs.getString("position"),
                rs.getString("department"),
                rs.getString("supervisor"),
                rs.getInt("basic_salary"),
                rs.getDouble("hourly_rate")
                
            );
        } else {
            System.out.println("No employee found with username: " + username); 
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving employee details: " + e.getMessage());
    }
    return null;
}

public static ResultSet getLeave() {
    String sql = "SELECT id, employee_id, leave_type, start_date, end_date, status  FROM leave_request";
    try {
        Connection conn = getConnection(); 
        if (conn == null || conn.isClosed()) {
            System.out.println("Database connection is closed. Reconnecting...");
            conn = DriverManager.getConnection(URL); // Reconnect
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery(); 
    } catch (SQLException e) {
        System.out.println("Error retrieving Leave Requests: " + e.getMessage());
        return null;
    }
}


public static ResultSet getPayrollByEmployeeId(String employeeId) {
    String sql = "SELECT employee_id, period_start , period_end, working_hours, overtime_hours, sss_contribution, philhealth_contribution, pagibig_contribution, witholding_tax, rice_subsidy, phone_allowance, clothing_allowance " +
                 "FROM payroll WHERE employee_id = ?";
    try {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, employeeId);
        return pstmt.executeQuery();
    } catch (SQLException e) {
        System.out.println("Error retrieving payroll data: " + e.getMessage());
        return null;
    }
}


public static boolean requestLeave(String id, String employeeId, String leaveType, String startDate, String endDate, String status) {
    String sql = "INSERT INTO leave_request (id, employee_id, leave_type, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, id);
        pstmt.setString(2, employeeId);
        pstmt.setString(3, leaveType);
        pstmt.setString(4, startDate);
        pstmt.setString(5, endDate);
        pstmt.setString(6, status);

        int rowsInserted = pstmt.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        System.out.println("Error inserting leave request: " + e.getMessage());
        return false;
    }
}

public static boolean updateLeave(String id, String status) {
    String sql = "UPDATE leave_request SET status = ? WHERE id = ?";

    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, status);
        pstmt.setString(2, id);

        int rowsUpdated = pstmt.executeUpdate(); 
        return rowsUpdated > 0;
    } catch (SQLException e) {
        System.out.println("Error updating leave request: " + e.getMessage());
        return false;
    }
}


public static boolean updateEmployee(String employee_id, String last_name, String first_name, String username, String birthday,  
                                     String phone_number, String address,  String status, String position, 
                                     String supervisor, String department,  double basic_salary, double hourly_rate) {
    String sql = "UPDATE employee SET last_name = ?, first_name = ?, username = ? ,birthday = ?, phone_number = ?, address = ?,  status = ?, position = ?, supervisor = ?, department = ?, basic_salary = ?, hourly_rate = ? WHERE employee_id = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(13, employee_id);
        pstmt.setString(1, last_name);
        pstmt.setString(2, first_name);
        pstmt.setString(3, username);
        pstmt.setString(4, birthday);
        pstmt.setString(5, phone_number);
        pstmt.setString(6, address);
        pstmt.setString(7, status);
        pstmt.setString(8, position);
        pstmt.setString(9, supervisor);
        pstmt.setString(10, department);        
        pstmt.setDouble(11, basic_salary);
        pstmt.setDouble(12, hourly_rate);
        

        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        System.out.println("Error updating employee: " + e.getMessage());
        return false;
    }
}


public static boolean deleteEmployee(String employee_id) {
    String sqlUser = "DELETE FROM user WHERE id = ?";
    String sqlEmployee = "DELETE FROM employee WHERE employee_id = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement pstmtUser = conn.prepareStatement(sqlUser);
         PreparedStatement pstmtEmployee = conn.prepareStatement(sqlEmployee)) {

        // Start transaction
        conn.setAutoCommit(false);

        // Delete from user table first
        pstmtUser.setString(1, employee_id);
        pstmtUser.executeUpdate();

        // Delete from employee table
        pstmtEmployee.setString(1, employee_id);
        int rowsDeleted = pstmtEmployee.executeUpdate();

        // Commit transaction if successful
        conn.commit();
        return rowsDeleted > 0;

    } catch (SQLException e) {
        System.out.println("Error deleting employee: " + e.getMessage());
        return false;
        
    } 
}

public static ResultSet getBenefits(String employeeId) {
        String sql = "SELECT benefits.rice_subsidy, benefits.phone_allowance, benefits.clothing_allowance "
                + "FROM benefits "
                + "JOIN employee ON benefits.position = employee.position "
                + "WHERE employee.employee_id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error retrieving benefits: " + e.getMessage());
            return null;
        }
    }


public static ResultSet getEmployeeLeave(String employeeId) {
    String sql = "SELECT * FROM leave_request WHERE employee_id = ?";
    try {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, employeeId);
        return pstmt.executeQuery();
    } catch (SQLException e) {
        System.out.println("Error retrieving leave requests: " + e.getMessage());
        return null;
    }
}

    // Method to close the database connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection: " + e.getMessage());
            }
        }
    }
    
   public static void generatePayslip(String employeeId) {
        try {
            // 1. Compile JRXML file
            JasperReport report = JasperCompileManager.compileReport("payslip.jrxml");

            // 2. Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("employee_id", employeeId);

            // 3. Connect to your database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:MotorPH Payroll System.db");

            // 4. Fill the report
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);

            // 5. Show the report in a viewer
            JasperViewer.viewReport(print, false);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate payslip.");
        }
    }
}