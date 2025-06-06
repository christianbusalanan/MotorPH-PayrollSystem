/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.motorph.v4;

import java.awt.CardLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KrisChan
 */
public class PayrollStaffDB extends javax.swing.JFrame {

    /**
     * Creates new form PayrollStaffDB
     */
    public PayrollStaffDB() {
        initComponents();
        loadEmployeeData();
        setLocationRelativeTo(null);
        loadPayrollData();
    }
    
    private void loadEmployeeData() {
        DefaultTableModel model = (DefaultTableModel) employee_data.getModel();
        model.setRowCount(0); // Clear existing data

        ResultSet rs = Database.getAllEmployees();
        try {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("employee_id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getString("birthday"), // Converts SQL Date to Java Date
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    rs.getString("status"),
                    rs.getString("position"),
                    rs.getString("department"),
                    rs.getString("supervisor"),
                    rs.getInt("basic_salary"),
                   
                    
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage());
        }
    }
    
    
    public static double calculateSSSContribution(double compensation) {
        if (compensation < 3250) return 135.00;
        else if (compensation < 3750) return 157.50;
        else if (compensation < 4250) return 180.00;
        else if (compensation < 4750) return 202.50;
        else if (compensation < 5250) return 225.00;
        else if (compensation < 5750) return 247.50;
        else if (compensation < 6250) return 270.00;
        else if (compensation < 6750) return 292.50;
        else if (compensation < 7250) return 315.00;
        else if (compensation < 7750) return 337.50;
        else if (compensation < 8250) return 360.00;
        else if (compensation < 8750) return 382.50;
        else if (compensation < 9250) return 405.00;
        else if (compensation < 9750) return 427.50;
        else if (compensation < 10250) return 450.00;
        else if (compensation < 10750) return 472.50;
        else if (compensation < 11250) return 495.00;
        else if (compensation < 11750) return 517.50;
        else if (compensation < 12250) return 540.00;
        else if (compensation < 12750) return 562.50;
        else if (compensation < 13250) return 585.00;
        else if (compensation < 13750) return 607.50;
        else if (compensation < 14250) return 630.00;
        else if (compensation < 14750) return 652.50;
        else if (compensation < 15250) return 675.00;
        else if (compensation < 15750) return 697.50;
        else if (compensation < 16250) return 720.00;
        else if (compensation < 16750) return 742.50;
        else if (compensation < 17250) return 765.00;
        else if (compensation < 17750) return 787.50;
        else if (compensation < 18250) return 810.00;
        else if (compensation < 18750) return 832.50;
        else if (compensation < 19250) return 855.00;
        else if (compensation < 19750) return 877.50;
        else if (compensation < 20250) return 900.00;
        else if (compensation < 20750) return 922.50;
        else if (compensation < 21250) return 945.00;
        else if (compensation < 21750) return 967.50;
        else if (compensation < 22250) return 990.00;
        else if (compensation < 22750) return 1012.50;
        else if (compensation < 23250) return 1035.00;
        else if (compensation < 23750) return 1057.50;
        else if (compensation < 24250) return 1080.00;
        else if (compensation < 24750) return 1102.50;
        else return 1125.00; // 24,750 and above
    }
    
    
    public static double calculatePhilhealth(double salary){
        double philhealthcont = salary*0.015;
        return philhealthcont;
    }
    
    public static double calculatePagibig(double monthlySalary) {
        if (monthlySalary >= 1000 && monthlySalary <= 1500) {
            return 0.01 * monthlySalary;
        } else if (monthlySalary > 1500) {
            return 0.02 * monthlySalary;
        } else {
            return 0.00; // Below 1,000, no contribution
        }
    }
    
    public static double computeWithholdingTax(double monthlySalary) {
        if (monthlySalary <= 20832) {
            return 0.0; // No tax
        } else if (monthlySalary < 33333) {
            return (monthlySalary - 20833) * 0.20;
        } else if (monthlySalary < 66667) {
            return 2500 + (monthlySalary - 33333) * 0.25;
        } else if (monthlySalary < 166667) {
            return 10833 + (monthlySalary - 66667) * 0.30;
        } else if (monthlySalary < 666667) {
            return 40833.33 + (monthlySalary - 166667) * 0.32;
        } else {
            return 200833.33 + (monthlySalary - 666667) * 0.35;
        }
    }
    
    private void loadPayrollData() {
        DefaultTableModel model = (DefaultTableModel) payrollData.getModel();
        model.setRowCount(0); // Clear existing data

        ResultSet rs = Database.getPayrollDetails();
        try {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("employee_id"),
                    rs.getString("period_start"),
                    rs.getString("period_end"), 
                    rs.getString("working_hours"),
                    rs.getString("overtime_hours"),
                    rs.getString("sss_contribution"),
                    rs.getString("philhealth_contribution"),
                    rs.getString("pagibig_contribution"), 
                    rs.getString("witholding_tax"),
                    rs.getString("deductions"),
                    rs.getString("gross_pay"),
                    rs.getString("net_salary")
                   
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage());
        }
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
        jLabel1 = new javax.swing.JLabel();
        parentPanel = new javax.swing.JPanel();
        empData = new javax.swing.JScrollPane();
        employee_data = new javax.swing.JTable();
        processPayrollPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        payrollData = new javax.swing.JTable();
        label1 = new java.awt.Label();
        label3 = new java.awt.Label();
        label5 = new java.awt.Label();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        txtEmpID = new javax.swing.JTextField();
        txtHrsWorked = new javax.swing.JTextField();
        txtOvertime = new javax.swing.JTextField();
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
        lblgrossSalary = new java.awt.Label();
        lblpagibig = new java.awt.Label();
        lblSSS = new java.awt.Label();
        lblphilhealth = new java.awt.Label();
        lblSSS3 = new java.awt.Label();
        lbltax = new java.awt.Label();
        label14 = new java.awt.Label();
        txtPeriodStart = new javax.swing.JTextField();
        label15 = new java.awt.Label();
        txtPeriodEnd = new javax.swing.JTextField();

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/background.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        parentPanel.setBackground(new java.awt.Color(255, 255, 255));
        parentPanel.setLayout(new java.awt.CardLayout());

        employee_data.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        employee_data.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Employee ID", "Last Name", "First Name", "Birth Date", "Address", "Phone Number", "Status", "Position", "Department", "Supervisor",
                "Basic Salary"}
        ));
        empData.setViewportView(employee_data);

        parentPanel.add(empData, "card2");

        processPayrollPanel.setBackground(new java.awt.Color(255, 255, 255));
        processPayrollPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        payrollData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Start Date", "End Date", "Hours Worked", "Overtime", "SSS", "Philhealth", "Pagibig", "Witholding Tax", "Deductions", "Gross Pay", "Net Pay"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(payrollData);

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
        processPayrollPanel.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        label6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label6.setForeground(new java.awt.Color(153, 0, 153));
        label6.setText("Hours Worked");
        processPayrollPanel.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        label7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label7.setForeground(new java.awt.Color(153, 0, 153));
        label7.setText("Overtime");
        processPayrollPanel.add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txtEmpID.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        processPayrollPanel.add(txtEmpID, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 130, -1));

        txtHrsWorked.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        processPayrollPanel.add(txtHrsWorked, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 130, -1));

        txtOvertime.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        processPayrollPanel.add(txtOvertime, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 130, -1));

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
        processPayrollPanel.add(lblRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 130, -1));

        btnPP.setText("Process Payroll");
        btnPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPActionPerformed(evt);
            }
        });
        processPayrollPanel.add(btnPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 83, 150, 30));

        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });
        processPayrollPanel.add(btnCalculate, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 150, 30));

        lblDeductions.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblDeductions.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblDeductions, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 100, -1));

        label12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label12.setForeground(new java.awt.Color(153, 0, 153));
        label12.setText("Gross Salary");
        processPayrollPanel.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        lblgrossSalary.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblgrossSalary.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblgrossSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 130, -1));

        lblpagibig.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblpagibig.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblpagibig, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 100, -1));

        lblSSS.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblSSS.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblSSS, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 100, -1));

        lblphilhealth.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblphilhealth.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblphilhealth, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 100, -1));

        lblSSS3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lblSSS3.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lblSSS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 130, -1));

        lbltax.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lbltax.setForeground(new java.awt.Color(0, 0, 0));
        processPayrollPanel.add(lbltax, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 100, -1));

        label14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label14.setForeground(new java.awt.Color(153, 0, 153));
        label14.setText("Period Start");
        processPayrollPanel.add(label14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));
        label14.getAccessibleContext().setAccessibleName("Period Start");

        txtPeriodStart.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        processPayrollPanel.add(txtPeriodStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 130, -1));

        label15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label15.setForeground(new java.awt.Color(153, 0, 153));
        label15.setText("Period End");
        processPayrollPanel.add(label15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        txtPeriodEnd.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        processPayrollPanel.add(txtPeriodEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 130, -1));

        parentPanel.add(processPayrollPanel, "card3");

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
    String employeeId = txtEmpID.getText().trim();
    String startDate = txtPeriodStart.getText().trim();
    String endDate = txtPeriodEnd.getText().trim();

    if (startDate.isEmpty() || employeeId.isEmpty() || endDate.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Date and Employee ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        double hourlyRate = Double.parseDouble(lblRate.getText());
        int hoursWorked = Integer.parseInt(txtHrsWorked.getText());
        int overtimeHours = Integer.parseInt(txtOvertime.getText());
        double sss = Double.parseDouble(lblSSS.getText());
        double philhealth = Double.parseDouble(lblphilhealth.getText());
        double pagibig = Double.parseDouble(lblpagibig.getText());
        double witholding_tax = Double.parseDouble(lbltax.getText());
        double deductions = Double.parseDouble(lblDeductions.getText());
        double gross = Double.parseDouble(lblgrossSalary.getText());
        double netSalary = Double.parseDouble(lblNetSalary.getText());

        boolean success = Database.insertPayrollRecord(employeeId, startDate, endDate, hoursWorked, overtimeHours, sss, philhealth, pagibig, witholding_tax, deductions, gross, netSalary);

        if (success) {
            JOptionPane.showMessageDialog(this, "Payroll processed successfully!");
            loadPayrollData(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error processing payroll.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_btnPPActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        // TODO add your handling code here:
        String employeeId = txtEmpID.getText().trim();
        if (employeeId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an Employee ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    double hourlyRate = Database.getHourlyRate(employeeId);   
    double basicSalary = Database.getBasicSalary(employeeId);
    
    if (hourlyRate > 0) {
        lblRate.setText(String.format("%.2f", hourlyRate)); // Display the rate
    } else {
        JOptionPane.showMessageDialog(this, "Employee not found or has no hourly rate.", "Search Error", JOptionPane.ERROR_MESSAGE);
        lblRate.setText(""); 
    }
        double hrsWorked= Double.parseDouble(txtHrsWorked.getText());
        double overtime= Double.parseDouble(txtOvertime.getText());        
        double rate= Double.parseDouble(lblRate.getText());   
        double grossSalary= rate* (hrsWorked+overtime);
                
        double sss = calculateSSSContribution(grossSalary);
        double philhealth = calculatePhilhealth(grossSalary);
        double pagibig = calculatePagibig(grossSalary);
        double taxableIncome = grossSalary - (sss + philhealth + pagibig); 
        double witholdingtax = computeWithholdingTax(taxableIncome);
             
                
        double deductions= sss+philhealth+witholdingtax+pagibig;
        
        double netSalary= grossSalary-deductions;
        
        
        lblRate.setText(String.format("%.2f", hourlyRate));
        lblNetSalary.setText(String.format("%.2f", netSalary));
        lblDeductions.setText(String.format("%.2f", deductions));
        lblgrossSalary.setText(String.format("%.2f", grossSalary));
        lblSSS.setText(String.format("%.2f", sss));
        lblphilhealth.setText(String.format("%.2f", philhealth));
        lblpagibig.setText(String.format("%.2f", pagibig));
        lbltax.setText(String.format("%.2f", witholdingtax));
    }//GEN-LAST:event_btnCalculateActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PayrollStaffDB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnPP;
    private javax.swing.JScrollPane empData;
    private javax.swing.JTable employee_data;
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
    private java.awt.Label label3;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.Label lblDeductions;
    private java.awt.Label lblNetSalary;
    private java.awt.Label lblRate;
    private java.awt.Label lblSSS;
    private java.awt.Label lblSSS3;
    private java.awt.Label lblgrossSalary;
    private java.awt.Label lblpagibig;
    private java.awt.Label lblphilhealth;
    private java.awt.Label lbltax;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JTable payrollData;
    private javax.swing.JButton processPayroll;
    private javax.swing.JPanel processPayrollPanel;
    private javax.swing.JTextField txtEmpID;
    private javax.swing.JTextField txtHrsWorked;
    private javax.swing.JTextField txtOvertime;
    private javax.swing.JTextField txtPeriodEnd;
    private javax.swing.JTextField txtPeriodStart;
    private javax.swing.JButton viewEmp;
    // End of variables declaration//GEN-END:variables
}
