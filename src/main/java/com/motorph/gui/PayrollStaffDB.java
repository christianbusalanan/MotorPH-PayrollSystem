/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.motorph.gui;

import com.motorph.dao.AttendanceDAO;
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

/**
 *
 * @author KrisChan
 */
public class PayrollStaffDB extends javax.swing.JFrame {
    private final EmployeeService employeeService;
    private final PayrollService payrollService;
    private final AttendanceDAO attendanceDAO;
    /**
     * Creates new form PayrollStaffDB
     */
    public PayrollStaffDB() {
        this.employeeService = new EmployeeService();
        this.payrollService = new PayrollService();
        this.attendanceDAO = new AttendanceDAO();
        initComponents();
        loadEmployeeData();
        loadPayrollData();
        loadAttendanceData();
        setLocationRelativeTo(null);
        
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
   
   private void loadAttendanceData() {
        
        try {
            List<AttendanceDAO.AttendanceWithEmployee> attendanceRecords = attendanceDAO.getAllAttendanceWithEmployeeDetails();
            
            
            DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            for (AttendanceDAO.AttendanceWithEmployee attendance : attendanceRecords) {
                Object[] row = {
                    attendance.getEmployeeId() != null ? attendance.getEmployeeId() : "N/A",
                    attendance.getLastName() != null ? attendance.getLastName() : "N/A",
                    attendance.getFirstName() != null ? attendance.getFirstName() : "N/A",
                    attendance.getDate() != null ? attendance.getDate().toString() : "N/A",
                    attendance.getTimeIn() != null ? attendance.getTimeIn().toString() : "0:00",
                    attendance.getTimeOut() != null ? attendance.getTimeOut().toString() : "0:00"
                };
                model.addRow(row);
               
            }
            
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        viewEmp = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        processPayroll = new javax.swing.JButton();
        btnAttendance = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        parentPanel = new javax.swing.JPanel();
        empData = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        processPayrollPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        payrollTable = new javax.swing.JTable();
        label1 = new java.awt.Label();
        label3 = new java.awt.Label();
        label5 = new java.awt.Label();
        label6 = new java.awt.Label();
        txtEmployeeId = new javax.swing.JTextField();
        label8 = new java.awt.Label();
        label9 = new java.awt.Label();
        label10 = new java.awt.Label();
        label11 = new java.awt.Label();
        lblNetSalary = new java.awt.Label();
        label13 = new java.awt.Label();
        lblRate = new java.awt.Label();
        btnPP = new javax.swing.JButton();
        btnCalculate = new javax.swing.JButton();
        lblDeductions = new java.awt.Label();
        label12 = new java.awt.Label();
        lblGrossSalary = new java.awt.Label();
        lblPagibig = new java.awt.Label();
        lblSSS = new java.awt.Label();
        lblPhilHealth = new java.awt.Label();
        lblSSS3 = new java.awt.Label();
        lblTax = new java.awt.Label();
        label14 = new java.awt.Label();
        txtPeriodStart = new javax.swing.JTextField();
        label15 = new java.awt.Label();
        txtPeriodEnd = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        label16 = new java.awt.Label();
        label17 = new java.awt.Label();
        lblRice = new java.awt.Label();
        lblClothing = new java.awt.Label();
        lblPhone = new java.awt.Label();
        lblHoursWorked = new java.awt.Label();
        panelAttendance = new javax.swing.JScrollPane();
        attendanceTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PAYROLL DASHBOARD");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viewEmp.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        viewEmp.setText("View Employees");
        viewEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEmpActionPerformed(evt);
            }
        });
        jPanel1.add(viewEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 180, 40));

        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jButton1.setText("Log Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 180, 40));

        processPayroll.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        processPayroll.setText("Process Payroll");
        processPayroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processPayrollActionPerformed(evt);
            }
        });
        jPanel1.add(processPayroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 180, 40));

        btnAttendance.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnAttendance.setText("Attendance");
        btnAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttendanceActionPerformed(evt);
            }
        });
        jPanel1.add(btnAttendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 180, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/background.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        parentPanel.setBackground(new java.awt.Color(255, 255, 255));
        parentPanel.setLayout(new java.awt.CardLayout());

        employeeTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        employeeTable.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Employee ID", "Last Name", "First Name", "Birth Date", "Address", "Phone Number", "Status", "Position", "Department", "Supervisor",
                "Basic Salary"}
        ));
        empData.setViewportView(employeeTable);

        parentPanel.add(empData, "card2");

        processPayrollPanel.setBackground(new java.awt.Color(255, 255, 255));
        processPayrollPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        payrollTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Start Date", "End Date", "Hours Worked", "SSS", "Philhealth", "Pagibig", "Witholding Tax", "Rice Subsidy", "Phone Allowance", "Clothing Allowance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(payrollTable);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 230));

        processPayrollPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 740, 230));

        label1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(153, 0, 153));
        label1.setName(""); // NOI18N
        label1.setText("SSS");
        processPayrollPanel.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        label3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(153, 0, 153));
        label3.setText("Employee ID");
        processPayrollPanel.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        label5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(153, 0, 153));
        label5.setText("Rate");
        processPayrollPanel.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        label6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label6.setForeground(new java.awt.Color(153, 0, 153));
        label6.setText("Hours Worked");
        processPayrollPanel.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        txtEmployeeId.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        processPayrollPanel.add(txtEmployeeId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 130, -1));

        label8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label8.setForeground(new java.awt.Color(153, 0, 153));
        label8.setText("Philhealth");
        processPayrollPanel.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        label9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label9.setForeground(new java.awt.Color(153, 0, 153));
        label9.setText("Witholding Tax");
        processPayrollPanel.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 120, -1));

        label10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label10.setForeground(new java.awt.Color(153, 0, 153));
        label10.setText("Net Salary");
        processPayrollPanel.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        label11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label11.setForeground(new java.awt.Color(153, 0, 153));
        label11.setText("PAGIBIG");
        processPayrollPanel.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        lblNetSalary.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblNetSalary.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblNetSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 100, -1));

        label13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label13.setForeground(new java.awt.Color(153, 0, 153));
        label13.setText("Deductions");
        processPayrollPanel.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        lblRate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblRate.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 130, -1));

        btnPP.setText("Process Payroll");
        btnPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPActionPerformed(evt);
            }
        });
        processPayrollPanel.add(btnPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, 150, 30));

        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });
        processPayrollPanel.add(btnCalculate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 150, 30));

        lblDeductions.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblDeductions.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblDeductions, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 100, -1));

        label12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label12.setForeground(new java.awt.Color(153, 0, 153));
        label12.setText("Gross Salary");
        processPayrollPanel.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        lblGrossSalary.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblGrossSalary.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblGrossSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 130, -1));

        lblPagibig.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblPagibig.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblPagibig, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 100, -1));

        lblSSS.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblSSS.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblSSS, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 100, -1));

        lblPhilHealth.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblPhilHealth.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblPhilHealth, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 100, -1));

        lblSSS3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblSSS3.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblSSS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 130, -1));

        lblTax.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblTax.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblTax, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 100, -1));

        label14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label14.setForeground(new java.awt.Color(153, 0, 153));
        label14.setText("Period Start");
        processPayrollPanel.add(label14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        txtPeriodStart.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        processPayrollPanel.add(txtPeriodStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 130, -1));

        label15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label15.setForeground(new java.awt.Color(153, 0, 153));
        label15.setText("Period End");
        processPayrollPanel.add(label15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        txtPeriodEnd.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        processPayrollPanel.add(txtPeriodEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 130, -1));

        label2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(153, 0, 153));
        label2.setName(""); // NOI18N
        label2.setText("Rice Subsidy");
        processPayrollPanel.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 100, -1));

        label16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label16.setForeground(new java.awt.Color(153, 0, 153));
        label16.setText("Phone Allowance");
        processPayrollPanel.add(label16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        label17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label17.setForeground(new java.awt.Color(153, 0, 153));
        label17.setText("Clothing Allowance");
        processPayrollPanel.add(label17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));

        lblRice.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblRice.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblRice, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 90, -1));

        lblClothing.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblClothing.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblClothing, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 90, -1));

        lblPhone.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblPhone.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 90, -1));

        lblHoursWorked.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblHoursWorked.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblHoursWorked, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 130, -1));

        parentPanel.add(processPayrollPanel, "card3");

        attendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Last Name", "First Name", "Date", "Time-in", "Time-out"
            }
        ));
        panelAttendance.setViewportView(attendanceTable);

        parentPanel.add(panelAttendance, "card5");

        getContentPane().add(parentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 760, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION) {
        dispose(); // Close current dashboard
        new LoginForm().setVisible(true); // Return to login screen
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void viewEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEmpActionPerformed
        // TODO add your handling code here:
        parentPanel.removeAll();
        parentPanel.add(empData);
        parentPanel.repaint();
        parentPanel.revalidate();
    }//GEN-LAST:event_viewEmpActionPerformed

    private void processPayrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processPayrollActionPerformed
        // TODO add your handling code here:
        parentPanel.removeAll();
        parentPanel.add(processPayrollPanel);
        parentPanel.repaint();
        parentPanel.revalidate();
    }//GEN-LAST:event_processPayrollActionPerformed

    private void btnPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPActionPerformed
        // TODO add your handling code here:
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
        
    }//GEN-LAST:event_btnPPActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        // TODO add your handling code here:
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
    
    }//GEN-LAST:event_btnCalculateActionPerformed

    private void btnAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttendanceActionPerformed
        // TODO add your handling code here:
        parentPanel.removeAll();
        parentPanel.add(panelAttendance);
        parentPanel.repaint();
        parentPanel.revalidate();
    }//GEN-LAST:event_btnAttendanceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PayrollStaffDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayrollStaffDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayrollStaffDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayrollStaffDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayrollStaffDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new PayrollStaffDB().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendanceTable;
    private javax.swing.JButton btnAttendance;
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnPP;
    private javax.swing.JScrollPane empData;
    private javax.swing.JTable employeeTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label14;
    private java.awt.Label label15;
    private java.awt.Label label16;
    private java.awt.Label label17;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.Label lblClothing;
    private java.awt.Label lblDeductions;
    private java.awt.Label lblGrossSalary;
    private java.awt.Label lblHoursWorked;
    private java.awt.Label lblNetSalary;
    private java.awt.Label lblPagibig;
    private java.awt.Label lblPhilHealth;
    private java.awt.Label lblPhone;
    private java.awt.Label lblRate;
    private java.awt.Label lblRice;
    private java.awt.Label lblSSS;
    private java.awt.Label lblSSS3;
    private java.awt.Label lblTax;
    private javax.swing.JScrollPane panelAttendance;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JTable payrollTable;
    private javax.swing.JButton processPayroll;
    private javax.swing.JPanel processPayrollPanel;
    private javax.swing.JTextField txtEmployeeId;
    private javax.swing.JTextField txtPeriodEnd;
    private javax.swing.JTextField txtPeriodStart;
    private javax.swing.JButton viewEmp;
    // End of variables declaration//GEN-END:variables
}
