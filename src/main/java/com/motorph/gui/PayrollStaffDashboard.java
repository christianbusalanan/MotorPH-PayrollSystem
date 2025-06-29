package com.motorph.gui;

import com.motorph.model.Employee;
import com.motorph.model.Payroll;
import com.motorph.service.EmployeeService;
import com.motorph.service.PayrollService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class PayrollStaffDashboard extends JFrame {
    private final EmployeeService employeeService;
    private final PayrollService payrollService;
    
    // UI Components
    private JPanel parentPanel;
    private JTable employeeTable;
    private JTable payrollTable;
    private JTextField txtEmployeeId, txtPeriodStart, txtPeriodEnd;
    private JLabel lblRate, lblHoursWorked, lblGrossSalary, lblDeductions, lblNetSalary;
    private JLabel lblSSS, lblPhilHealth, lblPagibig, lblTax;
    private JLabel lblRice, lblPhone, lblClothing;

    public PayrollStaffDashboard() {
        this.employeeService = new EmployeeService();
        this.payrollService = new PayrollService();
        initComponents();
        loadEmployeeData();
        loadPayrollData();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PAYROLL DASHBOARD");
        setSize(960, 500);
        getContentPane().setLayout(new BorderLayout());

        // Create sidebar
        JPanel sidebar = createSidebar();
        getContentPane().add(sidebar, BorderLayout.WEST);

        // Create main panel with CardLayout
        parentPanel = new JPanel(new CardLayout());
        getContentPane().add(parentPanel, BorderLayout.CENTER);

        // Add panels
        parentPanel.add(createEmployeeListPanel(), "employees");
        parentPanel.add(createPayrollProcessPanel(), "process_payroll");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 500));
        sidebar.setBackground(new Color(102, 0, 102));

        JButton btnViewEmployees = new JButton("View Employees");
        JButton btnProcessPayroll = new JButton("Process Payroll");
        JButton btnLogout = new JButton("Log Out");

        btnViewEmployees.addActionListener(e -> showPanel("employees"));
        btnProcessPayroll.addActionListener(e -> showPanel("process_payroll"));
        btnLogout.addActionListener(this::logoutActionPerformed);

        sidebar.add(Box.createVerticalStrut(50));
        sidebar.add(btnViewEmployees);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnProcessPayroll);
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

    private JPanel createPayrollProcessPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initialize components
        txtEmployeeId = new JTextField(15);
        txtPeriodStart = new JTextField(15);
        txtPeriodEnd = new JTextField(15);
        
        lblRate = new JLabel("0.00");
        lblHoursWorked = new JLabel("0.00");
        lblGrossSalary = new JLabel("0.00");
        lblDeductions = new JLabel("0.00");
        lblNetSalary = new JLabel("0.00");
        lblSSS = new JLabel("0.00");
        lblPhilHealth = new JLabel("0.00");
        lblPagibig = new JLabel("0.00");
        lblTax = new JLabel("0.00");
        lblRice = new JLabel("0.00");
        lblPhone = new JLabel("0.00");
        lblClothing = new JLabel("0.00");

        // Add input fields
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        panel.add(txtEmployeeId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Period Start (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        panel.add(txtPeriodStart, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Period End (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        panel.add(txtPeriodEnd, gbc);

        // Add calculation results
        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("Hourly Rate:"), gbc);
        gbc.gridx = 3;
        panel.add(lblRate, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JLabel("Hours Worked:"), gbc);
        gbc.gridx = 3;
        panel.add(lblHoursWorked, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        panel.add(new JLabel("Gross Salary:"), gbc);
        gbc.gridx = 3;
        panel.add(lblGrossSalary, gbc);

        // Add deductions
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("SSS:"), gbc);
        gbc.gridx = 1;
        panel.add(lblSSS, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("PhilHealth:"), gbc);
        gbc.gridx = 1;
        panel.add(lblPhilHealth, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("PAGIBIG:"), gbc);
        gbc.gridx = 1;
        panel.add(lblPagibig, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Withholding Tax:"), gbc);
        gbc.gridx = 1;
        panel.add(lblTax, gbc);

        // Add benefits
        gbc.gridx = 2; gbc.gridy = 4;
        panel.add(new JLabel("Rice Subsidy:"), gbc);
        gbc.gridx = 3;
        panel.add(lblRice, gbc);

        gbc.gridx = 2; gbc.gridy = 5;
        panel.add(new JLabel("Phone Allowance:"), gbc);
        gbc.gridx = 3;
        panel.add(lblPhone, gbc);

        gbc.gridx = 2; gbc.gridy = 6;
        panel.add(new JLabel("Clothing Allowance:"), gbc);
        gbc.gridx = 3;
        panel.add(lblClothing, gbc);

        // Add summary
        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(new JLabel("Total Deductions:"), gbc);
        gbc.gridx = 1;
        panel.add(lblDeductions, gbc);

        gbc.gridx = 2; gbc.gridy = 9;
        panel.add(new JLabel("Net Salary:"), gbc);
        gbc.gridx = 3;
        panel.add(lblNetSalary, gbc);

        // Add buttons
        JButton btnCalculate = new JButton("Calculate");
        JButton btnProcessPayroll = new JButton("Process Payroll");

        btnCalculate.addActionListener(this::calculatePayrollActionPerformed);
        btnProcessPayroll.addActionListener(this::processPayrollActionPerformed);

        gbc.gridx = 0; gbc.gridy = 11;
        panel.add(btnCalculate, gbc);
        gbc.gridx = 1;
        panel.add(btnProcessPayroll, gbc);

        // Add payroll table at the bottom
        payrollTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(payrollTable);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        
        gbc.gridx = 0; gbc.gridy = 12;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        return panel;
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) parentPanel.getLayout();
        cl.show(parentPanel, panelName);
        
        if ("employees".equals(panelName)) {
            loadEmployeeData();
        } else if ("process_payroll".equals(panelName)) {
            loadPayrollData();
        }
    }

    private void loadEmployeeData() {
        List<Employee> employees = employeeService.getAllEmployees();
        String[] columnNames = {"Employee ID", "Last Name", "First Name", "Birthday", 
                               "Address", "Phone", "Status", "Position", "Department", 
                               "Supervisor", "Basic Salary"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Employee emp : employees) {
            Object[] row = {
                emp.getEmployeeId(), emp.getLastName(), emp.getFirstName(),
                emp.getBirthday(), emp.getAddress(), emp.getPhoneNumber(),
                emp.getStatus(), emp.getPosition(), emp.getDepartment(),
                emp.getSupervisor(), emp.getBasicSalary()
            };
            model.addRow(row);
        }
        
        employeeTable.setModel(model);
    }

    private void loadPayrollData() {
        List<Payroll> payrolls = payrollService.getAllPayrolls();
        String[] columnNames = {"Employee ID", "Period Start", "Period End", "Working Hours", 
                               "SSS", "PhilHealth", "PAGIBIG", "Withholding Tax", 
                               "Rice Subsidy", "Phone Allowance", "Clothing Allowance"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (Payroll payroll : payrolls) {
            Object[] row = {
                payroll.getEmployeeId(), payroll.getPeriodStart(), payroll.getPeriodEnd(),
                payroll.getWorkingHours(), payroll.getSssContribution(), 
                payroll.getPhilhealthContribution(), payroll.getPagibigContribution(),
                payroll.getWithholdingTax(), payroll.getRiceSubsidy(),
                payroll.getPhoneAllowance(), payroll.getClothingAllowance()
            };
            model.addRow(row);
        }
        
        payrollTable.setModel(model);
    }

    private void calculatePayrollActionPerformed(ActionEvent evt) {
        try {
            String employeeId = txtEmployeeId.getText().trim();
            String periodStartStr = txtPeriodStart.getText().trim();
            String periodEndStr = txtPeriodEnd.getText().trim();
            
            if (employeeId.isEmpty() || periodStartStr.isEmpty() || periodEndStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            LocalDate periodStart = LocalDate.parse(periodStartStr);
            LocalDate periodEnd = LocalDate.parse(periodEndStr);
            
            Payroll payroll = payrollService.calculatePayroll(employeeId, periodStart, periodEnd);
            
            if (payroll != null) {
                Employee employee = employeeService.getEmployeeById(employeeId);
                
                // Update UI with calculated values
                lblRate.setText(String.format("%.2f", employee.getHourlyRate()));
                lblHoursWorked.setText(String.format("%.2f", payroll.getWorkingHours()));
                lblGrossSalary.setText(String.format("%.2f", payroll.getGrossIncome(employee.getHourlyRate())));
                lblSSS.setText(String.format("%.2f", payroll.getSssContribution()));
                lblPhilHealth.setText(String.format("%.2f", payroll.getPhilhealthContribution()));
                lblPagibig.setText(String.format("%.2f", payroll.getPagibigContribution()));
                lblTax.setText(String.format("%.2f", payroll.getWithholdingTax()));
                lblRice.setText(String.format("%.2f", payroll.getRiceSubsidy()));
                lblPhone.setText(String.format("%.2f", payroll.getPhoneAllowance()));
                lblClothing.setText(String.format("%.2f", payroll.getClothingAllowance()));
                lblDeductions.setText(String.format("%.2f", payroll.getTotalDeductions()));
                lblNetSalary.setText(String.format("%.2f", payroll.getNetSalary(employee.getHourlyRate())));
                
                JOptionPane.showMessageDialog(this, "Payroll calculated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found or error calculating payroll.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void processPayrollActionPerformed(ActionEvent evt) {
        try {
            String employeeId = txtEmployeeId.getText().trim();
            String periodStartStr = txtPeriodStart.getText().trim();
            String periodEndStr = txtPeriodEnd.getText().trim();
            
            if (employeeId.isEmpty() || periodStartStr.isEmpty() || periodEndStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please calculate payroll first.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            LocalDate periodStart = LocalDate.parse(periodStartStr);
            LocalDate periodEnd = LocalDate.parse(periodEndStr);
            
            Payroll payroll = payrollService.calculatePayroll(employeeId, periodStart, periodEnd);
            
            if (payroll != null) {
                boolean success = payrollService.processPayroll(payroll);
                
                if (success) {
                    JOptionPane.showMessageDialog(this, "Payroll processed successfully!");
                    loadPayrollData();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error processing payroll.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error calculating payroll.", "Calculation Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Processing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtEmployeeId.setText("");
        txtPeriodStart.setText("");
        txtPeriodEnd.setText("");
        lblRate.setText("0.00");
        lblHoursWorked.setText("0.00");
        lblGrossSalary.setText("0.00");
        lblSSS.setText("0.00");
        lblPhilHealth.setText("0.00");
        lblPagibig.setText("0.00");
        lblTax.setText("0.00");
        lblRice.setText("0.00");
        lblPhone.setText("0.00");
        lblClothing.setText("0.00");
        lblDeductions.setText("0.00");
        lblNetSalary.setText("0.00");
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
            java.util.logging.Logger.getLogger(PayrollStaffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new PayrollStaffDashboard().setVisible(true));
    }
}