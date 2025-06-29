package com.motorph.view;

import com.motorph.model.Attendance;
import com.motorph.model.Employee;
import com.motorph.model.LeaveRequest;
import com.motorph.service.EmployeeService;
import com.motorph.service.LeaveRequestService;
import com.motorph.dao.AttendanceDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class HRManagerDashboard extends JFrame {
    private final EmployeeService employeeService;
    private final LeaveRequestService leaveRequestService;
    private final AttendanceDAO attendanceDAO;
    
    // UI Components
    private JPanel parentPanel;
    private JTable employeeTable;
    private JTable leaveTable;
    private JTable attendanceTable;
    private JTextField txtEmployeeId, txtFirstName, txtLastName, txtUsername, txtPassword;
    private JTextField txtBirthday, txtAddress, txtPhoneNumber, txtDepartment, txtSupervisor, txtSalary;
    private JComboBox<String> comboPosition, comboStatus, comboRole;
    private JTextField txtLeaveId;
    private JComboBox<String> comboLeaveStatus;
    private JTextField txtAttendanceEmployeeId;

    public HRManagerDashboard() {
        this.employeeService = new EmployeeService();
        this.leaveRequestService = new LeaveRequestService();
        this.attendanceDAO = new AttendanceDAO();
        initComponents();
        setLocationRelativeTo(null);
        
        // Load data after UI is initialized
        SwingUtilities.invokeLater(() -> {
            System.out.println("HRManagerDashboard: Loading initial data...");
            loadEmployeeData();
            loadLeaveData();
            loadAttendanceData();
        });
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
        parentPanel.add(createAttendancePanel(), "attendance");
        
        // Show employees panel by default
        showPanel("employees");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 500));
        sidebar.setBackground(new Color(102, 0, 102));

        JButton btnViewEmployees = new JButton("View Employees");
        JButton btnEmployeeDetails = new JButton("Employee Details");
        JButton btnLeaveRequests = new JButton("Leave Requests");
        JButton btnAttendance = new JButton("Attendance");
        JButton btnLogout = new JButton("Log Out");

        // Style buttons
        styleButton(btnViewEmployees);
        styleButton(btnEmployeeDetails);
        styleButton(btnLeaveRequests);
        styleButton(btnAttendance);
        styleButton(btnLogout);

        btnViewEmployees.addActionListener(e -> showPanel("employees"));
        btnEmployeeDetails.addActionListener(e -> showPanel("employee_details"));
        btnLeaveRequests.addActionListener(e -> showPanel("leave_requests"));
        btnAttendance.addActionListener(e -> showPanel("attendance"));
        btnLogout.addActionListener(this::logoutActionPerformed);

        sidebar.add(Box.createVerticalStrut(50));
        sidebar.add(btnViewEmployees);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnEmployeeDetails);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnLeaveRequests);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnAttendance);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);
        sidebar.add(Box.createVerticalStrut(20));

        return sidebar;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(153, 0, 153));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private JPanel createEmployeeListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Add title
        JLabel titleLabel = new JLabel("Employee List with User Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        titleLabel.setForeground(new Color(102, 0, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create table with proper model including username
        String[] columnNames = {"Employee ID", "Username", "Last Name", "First Name", "Birthday", 
                               "Address", "Phone", "Status", "Position", "Department", 
                               "Supervisor", "Basic Salary", "Hourly Rate"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        employeeTable = new JTable(model);
        employeeTable.setFont(new Font("Arial", Font.PLAIN, 12));
        employeeTable.setRowHeight(25);
        employeeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        employeeTable.getTableHeader().setBackground(new Color(102, 0, 102));
        employeeTable.getTableHeader().setForeground(Color.WHITE);
        employeeTable.setSelectionBackground(new Color(153, 0, 153));
        employeeTable.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Add refresh button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        JButton btnRefresh = new JButton("Refresh Data");
        btnRefresh.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        btnRefresh.setBackground(new Color(102, 0, 102));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.addActionListener(e -> {
            System.out.println("HRManagerDashboard: Manually refreshing employee data...");
            loadEmployeeData();
        });
        buttonPanel.add(btnRefresh);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }

    private JPanel createEmployeeDetailsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add title
        JLabel titleLabel = new JLabel("Employee Management");
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        titleLabel.setForeground(new Color(102, 0, 102));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
        panel.add(titleLabel, gbc);
        gbc.gridwidth = 1; // Reset

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
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        panel.add(txtEmployeeId, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 3;
        panel.add(comboRole, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        panel.add(txtFirstName, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 3;
        panel.add(txtLastName, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 3;
        panel.add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Birthday (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        panel.add(txtBirthday, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 3;
        panel.add(txtPhoneNumber, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        panel.add(txtAddress, gbc);
        gbc.gridwidth = 1; // Reset

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Position:"), gbc);
        gbc.gridx = 1;
        panel.add(comboPosition, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 3;
        panel.add(comboStatus, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        panel.add(txtDepartment, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Basic Salary:"), gbc);
        gbc.gridx = 3;
        panel.add(txtSalary, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(new JLabel("Supervisor:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        panel.add(txtSupervisor, gbc);
        gbc.gridwidth = 1; // Reset

        // Add buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnCreate = new JButton("Create");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        styleActionButton(btnCreate);
        styleActionButton(btnUpdate);
        styleActionButton(btnDelete);
        styleActionButton(btnClear);

        btnCreate.addActionListener(this::createEmployeeActionPerformed);
        btnUpdate.addActionListener(this::updateEmployeeActionPerformed);
        btnDelete.addActionListener(this::deleteEmployeeActionPerformed);
        btnClear.addActionListener(e -> clearEmployeeFields());

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 4;
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private void styleActionButton(JButton button) {
        button.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        button.setBackground(new Color(102, 0, 102));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 30));
    }

    private JPanel createLeaveRequestPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Add title
        JLabel titleLabel = new JLabel("Leave Request Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        titleLabel.setForeground(new Color(102, 0, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Top panel for controls
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(Color.WHITE);
        controlPanel.add(new JLabel("Leave ID:"));
        txtLeaveId = new JTextField(20);
        controlPanel.add(txtLeaveId);
        
        controlPanel.add(new JLabel("Status:"));
        comboLeaveStatus = new JComboBox<>(new String[]{"Pending", "Approved", "Rejected"});
        controlPanel.add(comboLeaveStatus);
        
        JButton btnUpdateLeave = new JButton("Update Leave Request");
        styleActionButton(btnUpdateLeave);
        btnUpdateLeave.addActionListener(this::updateLeaveActionPerformed);
        controlPanel.add(btnUpdateLeave);
        
        JButton btnRefreshLeave = new JButton("Refresh");
        styleActionButton(btnRefreshLeave);
        btnRefreshLeave.addActionListener(e -> loadLeaveData());
        controlPanel.add(btnRefreshLeave);
        
        panel.add(controlPanel, BorderLayout.NORTH);
        
        // Table for leave requests
        String[] columnNames = {"ID", "Employee ID", "Leave Type", "Start Date", "End Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        leaveTable = new JTable(model);
        leaveTable.setFont(new Font("Arial", Font.PLAIN, 12));
        leaveTable.setRowHeight(25);
        leaveTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        leaveTable.getTableHeader().setBackground(new Color(102, 0, 102));
        leaveTable.getTableHeader().setForeground(Color.WHITE);
        leaveTable.setSelectionBackground(new Color(153, 0, 153));
        leaveTable.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(leaveTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createAttendancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Add title
        JLabel titleLabel = new JLabel("Employee Attendance Records", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        titleLabel.setForeground(new Color(102, 0, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Top panel for controls
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(Color.WHITE);
        
        controlPanel.add(new JLabel("Filter by Employee ID:"));
        txtAttendanceEmployeeId = new JTextField(15);
        controlPanel.add(txtAttendanceEmployeeId);
        
        JButton btnFilterAttendance = new JButton("Filter");
        styleActionButton(btnFilterAttendance);
        btnFilterAttendance.addActionListener(this::filterAttendanceActionPerformed);
        controlPanel.add(btnFilterAttendance);
        
        JButton btnShowAllAttendance = new JButton("Show All");
        styleActionButton(btnShowAllAttendance);
        btnShowAllAttendance.addActionListener(e -> loadAttendanceData());
        controlPanel.add(btnShowAllAttendance);
        
        JButton btnRefreshAttendance = new JButton("Refresh");
        styleActionButton(btnRefreshAttendance);
        btnRefreshAttendance.addActionListener(e -> loadAttendanceData());
        controlPanel.add(btnRefreshAttendance);
        
        panel.add(controlPanel, BorderLayout.NORTH);
        
        // Table for attendance records
        String[] columnNames = {"Employee ID", "Date", "Time In", "Time Out", "Hours Worked"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        attendanceTable = new JTable(model);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 12));
        attendanceTable.setRowHeight(25);
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        attendanceTable.getTableHeader().setBackground(new Color(102, 0, 102));
        attendanceTable.getTableHeader().setForeground(Color.WHITE);
        attendanceTable.setSelectionBackground(new Color(153, 0, 153));
        attendanceTable.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, panelName);
        
        // Refresh data when showing panels
        switch (panelName) {
            case "employees" -> {
                System.out.println("HRManagerDashboard: Showing employees panel, loading data...");
                loadEmployeeData();
            }
            case "leave_requests" -> {
                System.out.println("HRManagerDashboard: Showing leave requests panel, loading data...");
                loadLeaveData();
            }
            case "attendance" -> {
                System.out.println("HRManagerDashboard: Showing attendance panel, loading data...");
                loadAttendanceData();
            }
        }
    }

    private void loadEmployeeData() {
        System.out.println("HRManagerDashboard: Starting to load employee data with user details...");
        
        try {
            // Use the new method that includes user details
            List<Employee> employees = employeeService.getAllEmployeesWithUserDetails();
            System.out.println("HRManagerDashboard: Retrieved " + employees.size() + " employees from service");
            
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            for (Employee emp : employees) {
                Object[] row = {
                    emp.getEmployeeId() != null ? emp.getEmployeeId() : "N/A",
                    emp.getUsername() != null ? emp.getUsername() : "N/A", // Username from user table
                    emp.getLastName() != null ? emp.getLastName() : "N/A",
                    emp.getFirstName() != null ? emp.getFirstName() : "N/A",
                    emp.getBirthday() != null ? emp.getBirthday().toString() : "N/A",
                    emp.getAddress() != null ? emp.getAddress() : "N/A",
                    emp.getPhoneNumber() != null ? emp.getPhoneNumber() : "N/A",
                    emp.getStatus() != null ? emp.getStatus() : "N/A",
                    emp.getPosition() != null ? emp.getPosition() : "N/A",
                    emp.getDepartment() != null ? emp.getDepartment() : "N/A",
                    emp.getSupervisor() != null ? emp.getSupervisor() : "N/A",
                    String.format("%.2f", emp.getBasicSalary()),
                    String.format("%.2f", emp.getHourlyRate())
                };
                model.addRow(row);
                System.out.println("HRManagerDashboard: Added employee: " + emp.getEmployeeId() + 
                                 " - " + emp.getFullName() + " (username: " + emp.getUsername() + ")");
            }
            
            System.out.println("HRManagerDashboard: Employee table updated with " + model.getRowCount() + " rows");
            
            // Force table to refresh
            employeeTable.revalidate();
            employeeTable.repaint();
            
        } catch (Exception e) {
            System.out.println("HRManagerDashboard: Error loading employee data: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error loading employee data: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadLeaveData() {
        System.out.println("HRManagerDashboard: Starting to load leave data...");
        
        try {
            List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
            System.out.println("HRManagerDashboard: Retrieved " + leaveRequests.size() + " leave requests from service");
            
            DefaultTableModel model = (DefaultTableModel) leaveTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            for (LeaveRequest leave : leaveRequests) {
                Object[] row = {
                    leave.getId() != null ? leave.getId() : "N/A",
                    leave.getEmployeeId() != null ? leave.getEmployeeId() : "N/A",
                    leave.getLeaveType() != null ? leave.getLeaveType() : "N/A",
                    leave.getStartDate() != null ? leave.getStartDate().toString() : "N/A",
                    leave.getEndDate() != null ? leave.getEndDate().toString() : "N/A",
                    leave.getStatus() != null ? leave.getStatus() : "N/A"
                };
                model.addRow(row);
                System.out.println("HRManagerDashboard: Added leave request: " + leave.getId());
            }
            
            System.out.println("HRManagerDashboard: Leave table updated with " + model.getRowCount() + " rows");
            
            // Force table to refresh
            leaveTable.revalidate();
            leaveTable.repaint();
            
        } catch (Exception e) {
            System.out.println("HRManagerDashboard: Error loading leave data: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error loading leave data: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAttendanceData() {
        System.out.println("HRManagerDashboard: Starting to load attendance data...");
        
        try {
            List<Attendance> attendanceRecords = attendanceDAO.getAllAttendance();
            System.out.println("HRManagerDashboard: Retrieved " + attendanceRecords.size() + " attendance records");
            
            DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            for (Attendance attendance : attendanceRecords) {
                Object[] row = {
                    attendance.getEmployeeId() != null ? attendance.getEmployeeId() : "N/A",
                    attendance.getDate() != null ? attendance.getDate().toString() : "N/A",
                    attendance.getTimeIn() != null ? attendance.getTimeIn().toString() : "N/A",
                    attendance.getTimeOut() != null ? attendance.getTimeOut().toString() : "N/A",
                    String.format("%.2f", attendance.getHoursWorked())
                };
                model.addRow(row);
                System.out.println("HRManagerDashboard: Added attendance record for employee: " + 
                                 attendance.getEmployeeId() + " on " + attendance.getDate());
            }
            
            System.out.println("HRManagerDashboard: Attendance table updated with " + model.getRowCount() + " rows");
            
            // Force table to refresh
            attendanceTable.revalidate();
            attendanceTable.repaint();
            
        } catch (Exception e) {
            System.out.println("HRManagerDashboard: Error loading attendance data: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error loading attendance data: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterAttendanceActionPerformed(ActionEvent evt) {
        String employeeId = txtAttendanceEmployeeId.getText().trim();
        
        if (employeeId.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter an Employee ID to filter.", 
                "Input Required", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        System.out.println("HRManagerDashboard: Filtering attendance for employee: " + employeeId);
        
        try {
            List<Attendance> allAttendance = attendanceDAO.getAllAttendance();
            DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            int filteredCount = 0;
            for (Attendance attendance : allAttendance) {
                if (attendance.getEmployeeId() != null && 
                    attendance.getEmployeeId().toLowerCase().contains(employeeId.toLowerCase())) {
                    
                    Object[] row = {
                        attendance.getEmployeeId(),
                        attendance.getDate() != null ? attendance.getDate().toString() : "N/A",
                        attendance.getTimeIn() != null ? attendance.getTimeIn().toString() : "N/A",
                        attendance.getTimeOut() != null ? attendance.getTimeOut().toString() : "N/A",
                        String.format("%.2f", attendance.getHoursWorked())
                    };
                    model.addRow(row);
                    filteredCount++;
                }
            }
            
            System.out.println("HRManagerDashboard: Filtered attendance table updated with " + 
                             filteredCount + " records for employee: " + employeeId);
            
            if (filteredCount == 0) {
                JOptionPane.showMessageDialog(this, 
                    "No attendance records found for Employee ID: " + employeeId, 
                    "No Records Found", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
            // Force table to refresh
            attendanceTable.revalidate();
            attendanceTable.repaint();
            
        } catch (Exception e) {
            System.out.println("HRManagerDashboard: Error filtering attendance data: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error filtering attendance data: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createEmployeeActionPerformed(ActionEvent evt) {
        try {
            // Validate required fields
            if (txtEmployeeId.getText().trim().isEmpty() || 
                txtFirstName.getText().trim().isEmpty() || 
                txtLastName.getText().trim().isEmpty() ||
                txtUsername.getText().trim().isEmpty() ||
                txtPassword.getText().trim().isEmpty() ||
                txtSalary.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all required fields.", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            Employee employee = new Employee();
            employee.setEmployeeId(txtEmployeeId.getText().trim());
            employee.setFirstName(txtFirstName.getText().trim());
            employee.setLastName(txtLastName.getText().trim());
            employee.setUsername(txtUsername.getText().trim());
            
            // Parse birthday
            String birthdayStr = txtBirthday.getText().trim();
            if (!birthdayStr.isEmpty()) {
                try {
                    employee.setBirthday(LocalDate.parse(birthdayStr));
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(this, 
                        "Invalid birthday format. Please use YYYY-MM-DD.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            employee.setAddress(txtAddress.getText().trim());
            employee.setPhoneNumber(txtPhoneNumber.getText().trim());
            employee.setStatus((String) comboStatus.getSelectedItem());
            employee.setPosition((String) comboPosition.getSelectedItem());
            employee.setDepartment(txtDepartment.getText().trim());
            employee.setSupervisor(txtSupervisor.getText().trim());
            
            try {
                employee.setBasicSalary(Double.parseDouble(txtSalary.getText().trim()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid salary format. Please enter a valid number.", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            String password = txtPassword.getText().trim();
            String role = (String) comboRole.getSelectedItem();

            boolean success = employeeService.createEmployee(employee, password, role);

            if (success) {
                JOptionPane.showMessageDialog(this, "Employee created successfully!");
                clearEmployeeFields();
                loadEmployeeData();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error creating employee. Employee ID might already exist.", 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("HRManagerDashboard: Error creating employee: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error creating employee: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployeeActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Update functionality will be implemented in the next version.", 
            "Feature Not Available", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteEmployeeActionPerformed(ActionEvent evt) {
        String empId = JOptionPane.showInputDialog(this, 
            "Enter Employee ID to delete:", 
            "Delete Employee", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (empId != null && !empId.trim().isEmpty()) {
            String empName = employeeService.getEmployeeFullName(empId.trim());
            
            if (empName != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete '" + empName + "'?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = employeeService.deleteEmployee(empId.trim());
                    
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                        loadEmployeeData();
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "Error deleting employee.", 
                            "Database Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Employee not found.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateLeaveActionPerformed(ActionEvent evt) {
        String leaveId = txtLeaveId.getText().trim();
        String status = (String) comboLeaveStatus.getSelectedItem();
        
        if (leaveId.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a Leave ID.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean success = leaveRequestService.updateLeaveRequestStatus(leaveId, status);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Leave request updated successfully!");
            loadLeaveData();
            txtLeaveId.setText("");
        } else {
            JOptionPane.showMessageDialog(this, 
                "Error updating leave request. Leave ID might not exist.", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logoutActionPerformed(ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Logout", 
            JOptionPane.YES_NO_OPTION);
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
        comboPosition.setSelectedIndex(0);
        comboStatus.setSelectedIndex(0);
        comboRole.setSelectedIndex(0);
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