package com.motorph.view;

import com.motorph.model.Employee;
import com.motorph.model.LeaveRequest;
import com.motorph.model.Payroll;
import com.motorph.service.EmployeeService;
import com.motorph.service.LeaveRequestService;
import com.motorph.service.PayrollService;
import com.motorph.service.ReportService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDashboard extends JFrame {
    private final String username;
    private final EmployeeService employeeService;
    private final LeaveRequestService leaveRequestService;
    private final PayrollService payrollService;
    private final ReportService reportService;
    private Employee loggedInEmployee;
    
    // UI Components
    private JPanel parentPanel;
    private JTable leaveTable;
    private JTable payrollTable;
    private JTextField txtStartDate, txtEndDate;
    private JComboBox<String> comboLeaveType;
    private JLabel lblPayrollId;
    
    // Employee details labels
    private JLabel lblEmployeeId, lblFullName, lblBirthday, lblAddress, lblPhone;
    private JLabel lblStatus, lblPosition, lblDepartment, lblSupervisor;
    private JLabel lblBasicSalary, lblHourlyRate;

    public EmployeeDashboard(String username) {
        this.username = username;
        this.employeeService = new EmployeeService();
        this.leaveRequestService = new LeaveRequestService();
        this.payrollService = new PayrollService();
        this.reportService = new ReportService();
        
        initComponents();
        loadEmployeeDetails();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Dashboard");
        setSize(960, 500);
        getContentPane().setLayout(new BorderLayout());

        // Create sidebar
        JPanel sidebar = createSidebar();
        getContentPane().add(sidebar, BorderLayout.WEST);

        // Create main panel with CardLayout
        parentPanel = new JPanel(new CardLayout());
        getContentPane().add(parentPanel, BorderLayout.CENTER);

        // Add panels
        parentPanel.add(createEmployeeDetailsPanel(), "employee_details");
        parentPanel.add(createLeaveRequestPanel(), "leave_request");
        parentPanel.add(createPayrollPanel(), "payroll");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 500));
        sidebar.setBackground(new Color(102, 0, 102));

        JButton btnEmployeeDetails = new JButton("Employee Details");
        JButton btnRequestLeave = new JButton("Request Leave");
        JButton btnPayroll = new JButton("Payroll");
        JButton btnLogout = new JButton("Log Out");

        btnEmployeeDetails.addActionListener(e -> showPanel("employee_details"));
        btnRequestLeave.addActionListener(e -> {
            showPanel("leave_request");
            loadLeaveData();
        });
        btnPayroll.addActionListener(e -> {
            showPanel("payroll");
            loadPayrollData();
        });
        btnLogout.addActionListener(this::logoutActionPerformed);

        sidebar.add(Box.createVerticalStrut(50));
        sidebar.add(btnEmployeeDetails);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnRequestLeave);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnPayroll);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);
        sidebar.add(Box.createVerticalStrut(20));

        return sidebar;
    }

    private JPanel createEmployeeDetailsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Initialize labels
        lblEmployeeId = new JLabel();
        lblFullName = new JLabel();
        lblBirthday = new JLabel();
        lblAddress = new JLabel();
        lblPhone = new JLabel();
        lblStatus = new JLabel();
        lblPosition = new JLabel();
        lblDepartment = new JLabel();
        lblSupervisor = new JLabel();
        lblBasicSalary = new JLabel();
        lblHourlyRate = new JLabel();

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        panel.add(lblEmployeeId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        panel.add(lblFullName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Birthday:"), gbc);
        gbc.gridx = 1;
        panel.add(lblBirthday, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        panel.add(lblAddress, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        panel.add(lblPhone, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        panel.add(lblStatus, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Position:"), gbc);
        gbc.gridx = 1;
        panel.add(lblPosition, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        panel.add(lblDepartment, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(new JLabel("Supervisor:"), gbc);
        gbc.gridx = 1;
        panel.add(lblSupervisor, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(new JLabel("Basic Salary:"), gbc);
        gbc.gridx = 1;
        panel.add(lblBasicSalary, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        panel.add(new JLabel("Hourly Rate:"), gbc);
        gbc.gridx = 1;
        panel.add(lblHourlyRate, gbc);

        return panel;
    }

    private JPanel createLeaveRequestPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Top panel for leave request form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        txtStartDate = new JTextField(10);
        txtEndDate = new JTextField(10);
        comboLeaveType = new JComboBox<>(new String[]{
            "Vacation Leave", "Sick Leave", "Emergency Leave", "Maternity/Paternity Leave"
        });

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Start Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtStartDate, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("End Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtEndDate, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Leave Type:"), gbc);
        gbc.gridx = 1;
        formPanel.add(comboLeaveType, gbc);

        JButton btnSubmitLeave = new JButton("Submit Leave Request");
        btnSubmitLeave.addActionListener(this::submitLeaveActionPerformed);
        gbc.gridx = 2; gbc.gridy = 1;
        formPanel.add(btnSubmitLeave, gbc);

        // Add refresh button for debugging
        JButton btnRefreshLeave = new JButton("Refresh Leave Data");
        btnRefreshLeave.addActionListener(e -> {
            System.out.println("Manually refreshing leave data...");
            loadLeaveData();
        });
        gbc.gridx = 2; gbc.gridy = 2;
        formPanel.add(btnRefreshLeave, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        // Table for leave history
        leaveTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(leaveTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPayrollPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Top panel for payroll ID display and generate button
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Payroll ID:"));
        lblPayrollId = new JLabel("Click on a payroll record to select");
        topPanel.add(lblPayrollId);
        
        JButton btnGeneratePayslip = new JButton("Generate Payslip");
        btnGeneratePayslip.addActionListener(this::generatePayslipActionPerformed);
        topPanel.add(btnGeneratePayslip);
        
        panel.add(topPanel, BorderLayout.NORTH);
        
        // Table for payroll records
        payrollTable = new JTable();
        payrollTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                payrollTableMouseClicked(evt);
            }
        });
        JScrollPane scrollPane = new JScrollPane(payrollTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, panelName);
    }

    private void loadEmployeeDetails() {
        System.out.println("EmployeeDashboard: Loading employee details for username: " + username);
        
        // Use the new method that joins user and employee tables
        loggedInEmployee = employeeService.getEmployeeByUsername(username);
        
        if (loggedInEmployee == null) {
            System.out.println("EmployeeDashboard: Employee not found for username: " + username);
            JOptionPane.showMessageDialog(this, 
                "Error: Employee not found for username '" + username + "'.\n" +
                "Please check if the user account is properly linked to an employee record.", 
                "Employee Not Found", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("EmployeeDashboard: Employee found: " + loggedInEmployee.getEmployeeId() + 
                         " - " + loggedInEmployee.getFullName());
        
        // Update UI with employee details
        lblEmployeeId.setText(loggedInEmployee.getEmployeeId() != null ? loggedInEmployee.getEmployeeId() : "N/A");
        lblFullName.setText(loggedInEmployee.getFullName() != null ? loggedInEmployee.getFullName() : "N/A");
        lblBirthday.setText(loggedInEmployee.getBirthday() != null ? loggedInEmployee.getBirthday().toString() : "N/A");
        lblAddress.setText(loggedInEmployee.getAddress() != null ? loggedInEmployee.getAddress() : "N/A");
        lblPhone.setText(loggedInEmployee.getPhoneNumber() != null ? loggedInEmployee.getPhoneNumber() : "N/A");
        lblStatus.setText(loggedInEmployee.getStatus() != null ? loggedInEmployee.getStatus() : "N/A");
        lblPosition.setText(loggedInEmployee.getPosition() != null ? loggedInEmployee.getPosition() : "N/A");
        lblDepartment.setText(loggedInEmployee.getDepartment() != null ? loggedInEmployee.getDepartment() : "N/A");
        lblSupervisor.setText(loggedInEmployee.getSupervisor() != null ? loggedInEmployee.getSupervisor() : "N/A");
        lblBasicSalary.setText(String.format("%.2f", loggedInEmployee.getBasicSalary()));
        lblHourlyRate.setText(String.format("%.2f", loggedInEmployee.getHourlyRate()));
    }

    private void loadLeaveData() {
        System.out.println("EmployeeDashboard: Loading leave data...");
        
        if (loggedInEmployee == null) {
            System.out.println("EmployeeDashboard: Cannot load leave data: loggedInEmployee is null");
            JOptionPane.showMessageDialog(this, "Employee information not loaded. Please restart the application.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        System.out.println("EmployeeDashboard: Loading leave data for employee ID: " + loggedInEmployee.getEmployeeId());
        
        try {
            List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequestsByEmployeeId(loggedInEmployee.getEmployeeId());
            System.out.println("EmployeeDashboard: Retrieved " + leaveRequests.size() + " leave requests");
            
            String[] columnNames = {"ID", "Employee ID", "Leave Type", "Start Date", "End Date", "Status"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            
            for (LeaveRequest leave : leaveRequests) {
                Object[] row = {
                    leave.getId(), 
                    leave.getEmployeeId(), 
                    leave.getLeaveType(),
                    leave.getStartDate(), 
                    leave.getEndDate(), 
                    leave.getStatus()
                };
                model.addRow(row);
                System.out.println("EmployeeDashboard: Added leave request: " + leave.getId());
            }
            
            leaveTable.setModel(model);
            System.out.println("EmployeeDashboard: Leave table model updated with " + model.getRowCount() + " rows");
            
            // Force table to repaint
            leaveTable.revalidate();
            leaveTable.repaint();
            
        } catch (Exception e) {
            System.out.println("EmployeeDashboard: Error loading leave data: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading leave data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadPayrollData() {
        if (loggedInEmployee == null) return;
        
        List<Payroll> payrolls = payrollService.getPayrollsByEmployeeId(loggedInEmployee.getEmployeeId());
        String[] columnNames = {"Payroll ID", "Period Start", "Period End", "Working Hours", 
                               "SSS", "PhilHealth", "PAGIBIG", "Withholding Tax", 
                               "Rice Subsidy", "Phone Allowance", "Clothing Allowance"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Payroll payroll : payrolls) {
            Object[] row = {
                payroll.getPayrollId(), payroll.getPeriodStart(), payroll.getPeriodEnd(),
                payroll.getWorkingHours(), payroll.getSssContribution(), 
                payroll.getPhilhealthContribution(), payroll.getPagibigContribution(),
                payroll.getWithholdingTax(), payroll.getRiceSubsidy(),
                payroll.getPhoneAllowance(), payroll.getClothingAllowance()
            };
            model.addRow(row);
        }
        
        payrollTable.setModel(model);
    }

    private void submitLeaveActionPerformed(ActionEvent evt) {
        if (loggedInEmployee == null) {
            JOptionPane.showMessageDialog(this, "Employee information not loaded.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String startDateStr = txtStartDate.getText().trim();
            String endDateStr = txtEndDate.getText().trim();
            String leaveType = (String) comboLeaveType.getSelectedItem();
            
            if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Start Date and End Date cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
            
            System.out.println("EmployeeDashboard: Submitting leave request for employee: " + loggedInEmployee.getEmployeeId());
            
            boolean success = leaveRequestService.submitLeaveRequest(
                loggedInEmployee.getEmployeeId(), leaveType, startDate, endDate
            );
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Leave Request submitted successfully!");
                txtStartDate.setText("");
                txtEndDate.setText("");
                loadLeaveData(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(this, "Error submitting leave request.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("EmployeeDashboard: Error submitting leave request: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void payrollTableMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = payrollTable.getSelectedRow();
        if (selectedRow >= 0) {
            Object payrollIdObject = payrollTable.getModel().getValueAt(selectedRow, 0);
            if (payrollIdObject != null) {
                String payrollId = payrollIdObject.toString();
                lblPayrollId.setText(payrollId);
            } else {
                lblPayrollId.setText("N/A");
            }
        }
    }

    private void generatePayslipActionPerformed(ActionEvent evt) {
        String payrollId = lblPayrollId.getText().trim();
        if (payrollId.equals("Click on a payroll record to select") || payrollId.equals("N/A")) {
            JOptionPane.showMessageDialog(this, "Please select a payroll record first.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        reportService.generatePayslip(payrollId);
    }

    private void logoutActionPerformed(ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new LoginForm().setVisible(true);
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new EmployeeDashboard("testuser").setVisible(true));
    }
}