package com.motorph.gui;

import com.motorph.model.Employee;
import com.motorph.model.LeaveRequest;
import com.motorph.service.EmployeeService;
import com.motorph.service.LeaveRequestService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class HRManagerDashboard extends JFrame {
    private final EmployeeService employeeService;
    private final LeaveRequestService leaveRequestService;
    
    // UI Components
    private JPanel parentPanel;
    private JTable employeeTable;
    private JTable leaveTable;
    private JTextField txtEmployeeId, txtFirstName, txtLastName, txtUsername, txtPassword;
    private JTextField txtBirthday, txtAddress, txtPhoneNumber, txtDepartment, txtSupervisor, txtSalary;
    private JComboBox<String> comboPosition, comboStatus, comboRole;
    private JTextField txtLeaveId;
    private JComboBox<String> comboLeaveStatus;

    public HRManagerDashboard() {
        this.employeeService = new EmployeeService();
        this.leaveRequestService = new LeaveRequestService();
        initComponents();
        loadEmployeeData();
        loadLeaveData();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("HR MANAGER DASHBOARD");
        setSize(960, 502);
        getContentPane().setLayout(new BorderLayout());

        // Create sidebar
        JPanel sidebar = createSidebar();
        getContentPane().add(sidebar, BorderLayout.WEST);

        // Create main panel with CardLayout
        parentPanel = new JPanel(new CardLayout());
        getContentPane().add(parentPanel, BorderLayout.CENTER);

        // Add panels
        parentPanel.add(createEmployeeListPanel(), "employees");
        parentPanel.add(createEmployeeDetailsPanel(), "employee_details");
        parentPanel.add(createLeaveRequestPanel(), "leave_requests");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 500));
        sidebar.setBackground(new Color(102, 0, 102));

        JButton btnViewEmployees = new JButton("View Employees");
        JButton btnEmployeeDetails = new JButton("Employee Details");
        JButton btnLeaveRequests = new JButton("Leave Requests");
        JButton btnLogout = new JButton("Log Out");

        btnViewEmployees.addActionListener(e -> showPanel("employees"));
        btnEmployeeDetails.addActionListener(e -> showPanel("employee_details"));
        btnLeaveRequests.addActionListener(e -> showPanel("leave_requests"));
        btnLogout.addActionListener(this::logoutActionPerformed);

        sidebar.add(Box.createVerticalStrut(50));
        sidebar.add(btnViewEmployees);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnEmployeeDetails);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnLeaveRequests);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);
        sidebar.add(Box.createVerticalStrut(20));

        return sidebar;
    }

    private JPanel createEmployeeListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createEmployeeDetailsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initialize text fields
        txtEmployeeId = new JTextField(15);
        txtFirstName = new JTextField(15);
        txtLastName = new JTextField(15);
        txtUsername = new JTextField(15);
        txtPassword = new JTextField(15);
        txtBirthday = new JTextField(15);
        txtAddress = new JTextField(30);
        txtPhoneNumber = new JTextField(15);
        txtDepartment = new JTextField(15);
        txtSupervisor = new JTextField(30);
        txtSalary = new JTextField(15);

        // Initialize combo boxes
        comboPosition = new JComboBox<>(new String[]{
            "Chief Executive Officer", "Chief Operating Officer", "Chief Finance Officer",
            "Chief Marketing Officer", "IT Operations and Systems", "HR Manager",
            "HR Team Leader", "HR Rank and File", "Accounting Head", "Payroll Manager",
            "Payroll Team Leader", "Payroll Rank and File", "Account Manager",
            "Account Team Leader", "Account Rank and File", "Sales & Marketing",
            "Supply Chain and Logistics", "Customer Service and Relations"
        });
        
        comboStatus = new JComboBox<>(new String[]{"Regular", "Probationary"});
        comboRole = new JComboBox<>(new String[]{"Employee", "HR Manager", "Payroll Staff"});

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        panel.add(txtEmployeeId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        panel.add(txtFirstName, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 3;
        panel.add(txtLastName, gbc);

        // Add more fields...
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 3;
        panel.add(txtPassword, gbc);

        // Add buttons
        JPanel buttonPanel = new JPanel();
        JButton btnCreate = new JButton("Create");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        btnCreate.addActionListener(this::createEmployeeActionPerformed);
        btnUpdate.addActionListener(this::updateEmployeeActionPerformed);
        btnDelete.addActionListener(this::deleteEmployeeActionPerformed);

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 4;
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private JPanel createLeaveRequestPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Top panel for controls
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Leave ID:"));
        txtLeaveId = new JTextField(20);
        controlPanel.add(txtLeaveId);
        
        controlPanel.add(new JLabel("Status:"));
        comboLeaveStatus = new JComboBox<>(new String[]{"Pending", "Approved", "Rejected"});
        controlPanel.add(comboLeaveStatus);
        
        JButton btnUpdateLeave = new JButton("Update Leave Request");
        btnUpdateLeave.addActionListener(this::updateLeaveActionPerformed);
        controlPanel.add(btnUpdateLeave);
        
        panel.add(controlPanel, BorderLayout.NORTH);
        
        // Table for leave requests
        leaveTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(leaveTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, panelName);
        
        // Refresh data when showing panels
        switch (panelName) {
            case "employees" -> loadEmployeeData();
            case "leave_requests" -> loadLeaveData();
        }
    }

    private void loadEmployeeData() {
        List<Employee> employees = employeeService.getAllEmployees();
        String[] columnNames = {"Employee ID", "Last Name", "First Name", "Birthday", 
                               "Address", "Phone", "Status", "Position", "Department", 
                               "Supervisor", "Basic Salary", "Hourly Rate"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Employee emp : employees) {
            Object[] row = {
                emp.getEmployeeId(), emp.getLastName(), emp.getFirstName(),
                emp.getBirthday(), emp.getAddress(), emp.getPhoneNumber(),
                emp.getStatus(), emp.getPosition(), emp.getDepartment(),
                emp.getSupervisor(), emp.getBasicSalary(), emp.getHourlyRate()
            };
            model.addRow(row);
        }
        
        employeeTable.setModel(model);
    }

    private void loadLeaveData() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        String[] columnNames = {"ID", "Employee ID", "Leave Type", "Start Date", "End Date", "Status"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (LeaveRequest leave : leaveRequests) {
            Object[] row = {
                leave.getId(), leave.getEmployeeId(), leave.getLeaveType(),
                leave.getStartDate(), leave.getEndDate(), leave.getStatus()
            };
            model.addRow(row);
        }
        
        leaveTable.setModel(model);
    }

    private void createEmployeeActionPerformed(ActionEvent evt) {
        try {
            Employee employee = new Employee();
            employee.setEmployeeId(txtEmployeeId.getText().trim());
            employee.setFirstName(txtFirstName.getText().trim());
            employee.setLastName(txtLastName.getText().trim());
            employee.setUsername(txtUsername.getText().trim());
            employee.setBirthday(LocalDate.parse(txtBirthday.getText().trim()));
            employee.setAddress(txtAddress.getText().trim());
            employee.setPhoneNumber(txtPhoneNumber.getText().trim());
            employee.setStatus((String) comboStatus.getSelectedItem());
            employee.setPosition((String) comboPosition.getSelectedItem());
            employee.setDepartment(txtDepartment.getText().trim());
            employee.setSupervisor(txtSupervisor.getText().trim());
            employee.setBasicSalary(Double.parseDouble(txtSalary.getText().trim()));

            String password = txtPassword.getText().trim();
            String role = (String) comboRole.getSelectedItem();

            boolean success = employeeService.createEmployee(employee, password, role);

            if (success) {
                JOptionPane.showMessageDialog(this, "Employee created successfully!");
                clearEmployeeFields();
                loadEmployeeData();
            } else {
                JOptionPane.showMessageDialog(this, "Error creating employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployeeActionPerformed(ActionEvent evt) {
        // Implementation for update employee
        JOptionPane.showMessageDialog(this, "Update functionality not yet implemented.");
    }

    private void deleteEmployeeActionPerformed(ActionEvent evt) {
        String empId = JOptionPane.showInputDialog(this, "Enter Employee ID to delete:");
        
        if (empId != null && !empId.trim().isEmpty()) {
            String empName = employeeService.getEmployeeFullName(empId);
            
            if (empName != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete '" + empName + "'?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = employeeService.deleteEmployee(empId);
                    
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                        loadEmployeeData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error deleting employee.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateLeaveActionPerformed(ActionEvent evt) {
        String leaveId = txtLeaveId.getText().trim();
        String status = (String) comboLeaveStatus.getSelectedItem();
        
        if (leaveId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Leave ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean success = leaveRequestService.updateLeaveRequestStatus(leaveId, status);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Leave request updated successfully!");
            loadLeaveData();
            txtLeaveId.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error updating leave request.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logoutActionPerformed(ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new LoginForm().setVisible(true);
        }
    }

    private void clearEmployeeFields() {
        txtEmployeeId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtBirthday.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        txtDepartment.setText("");
        txtSupervisor.setText("");
        txtSalary.setText("");
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
            java.util.logging.Logger.getLogger(HRManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new HRManagerDashboard().setVisible(true));
    }
}