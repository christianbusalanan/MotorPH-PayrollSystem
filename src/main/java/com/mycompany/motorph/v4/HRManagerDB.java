/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.motorph.v4;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KrisChan
 */
public class HRManagerDB extends javax.swing.JFrame {

    /**
     * Creates new form HRManager
     */
    public HRManagerDB() {
        initComponents();
        loadEmployeeData();
        setLocationRelativeTo(null);
        loadLeaveData();
    }
    
    private void loadEmployeeData() {
        DefaultTableModel model = (DefaultTableModel) employee_data.getModel();
        model.setRowCount(0); 

        ResultSet rs = Database.getAllEmployees();
        try {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("employee_id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getString("birthday"), 
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    rs.getString("status"),
                    rs.getString("position"),
                    rs.getString("department"),
                    rs.getString("supervisor"),
                    rs.getInt("basic_salary"),
                    rs.getDouble("hourly_rate")
                   
                    
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage());
        }
    }
    

        private void loadLeaveData() {
        DefaultTableModel model = (DefaultTableModel) tableLeave.getModel();
        model.setRowCount(0); 

        ResultSet rs = Database.getLeave();
        try {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("employee_id"),
                    rs.getString("leave_type"),
                    rs.getString("start_date"), 
                    rs.getString("end_date"),
                    rs.getString("status"),
                   
                    
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading Leave data: " + e.getMessage());
        }
    }
    
    
   
    
    
    private void clearTextFields() {
    txtEmpID.setText("");
    txtlastName.setText("");
    txtfirstName.setText("");
    txtbirthday.setText("");
    txtaddress.setText("");
    txtphoneNumber.setText("");
    txtstatus.setText("");
    txtposition.setText("");
    txtdepartment.setText("");
    txtsupervisor.setText("");
    txtsalary.setText("");
    txtUsername.setText("");
    txtPassword.setText("");
    txtRole.setText("");
}
    
    private String getEmployeeName(String empId) {
    String sql = "SELECT first_name, last_name FROM employee WHERE employee_id = ?";
    try (Connection conn = Database.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, empId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getString("first_name") + " " + rs.getString("last_name");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving employee name: " + e.getMessage());
    }
    return null; 
}


private boolean deleteEmployee(String empId) {
    String sqlDeleteUser = "DELETE FROM user WHERE id = ?";
    String sqlDeleteEmployee = "DELETE FROM employee WHERE employee_id = ?";
    
    try (Connection conn = Database.getConnection()) {
        conn.setAutoCommit(false); // Start transaction
        
        try (PreparedStatement pstmtUser = conn.prepareStatement(sqlDeleteUser);
             PreparedStatement pstmtEmployee = conn.prepareStatement(sqlDeleteEmployee)) {
            
            // Step 1: Delete from user table
            pstmtUser.setString(1, empId);
            int userRows = pstmtUser.executeUpdate();
            
            // Step 2: Delete from employee table
            pstmtEmployee.setString(1, empId);
            int employeeRows = pstmtEmployee.executeUpdate();
            
            // Step 3: Check if both deletions were successful
            if (userRows > 0 && employeeRows > 0) {
                conn.commit(); // Commit transaction if both succeed
                return true;
            } else {
                conn.rollback(); // Rollback if any deletion fails
                System.out.println("Transaction rolled back. Deletion failed.");
                return false;
            }
        }
    } catch (SQLException e) {
        System.out.println("Error during deletion: " + e.getMessage());
    }
    return false;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        logout = new javax.swing.JButton();
        viewEmp = new javax.swing.JButton();
        empDetails = new javax.swing.JButton();
        btnLeave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        parentPanel = new javax.swing.JPanel();
        empData = new javax.swing.JScrollPane();
        employee_data = new javax.swing.JTable();
        employee_details = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        label8 = new java.awt.Label();
        label9 = new java.awt.Label();
        label10 = new java.awt.Label();
        label12 = new java.awt.Label();
        label13 = new java.awt.Label();
        txtfirstName = new javax.swing.JTextField();
        txtsalary = new javax.swing.JTextField();
        txtlastName = new javax.swing.JTextField();
        txtbirthday = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        txtsupervisor = new javax.swing.JTextField();
        txtEmpID = new javax.swing.JTextField();
        txtstatus = new javax.swing.JTextField();
        txtphoneNumber = new javax.swing.JTextField();
        txtposition = new javax.swing.JTextField();
        txtdepartment = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();
        btncreate = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        label15 = new java.awt.Label();
        txtUsername = new javax.swing.JTextField();
        label16 = new java.awt.Label();
        txtPassword = new javax.swing.JTextField();
        label17 = new java.awt.Label();
        txtRole = new javax.swing.JTextField();
        leavePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLeave = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        comboboxStatus = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HR MANAGER DASHBOARD");
        setSize(new java.awt.Dimension(960, 502));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 180, 40));

        viewEmp.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        viewEmp.setText("View Employees");
        viewEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEmpActionPerformed(evt);
            }
        });
        jPanel1.add(viewEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 180, 40));

        empDetails.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        empDetails.setText("Employee Details");
        empDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empDetailsActionPerformed(evt);
            }
        });
        jPanel1.add(empDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 180, 40));

        btnLeave.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnLeave.setText("Leave Requests");
        btnLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 180, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/background.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 500));

        parentPanel.setBackground(new java.awt.Color(255, 255, 255));
        parentPanel.setLayout(new java.awt.CardLayout());

        employee_data.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        employee_data.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Employee ID", "Last Name", "First Name", "Birth Date", "Address", "Phone Number", "Status", "Position", "Department", "Supervisor",
                "Basic Salary", "Rate"}
        ));
        empData.setViewportView(employee_data);

        parentPanel.add(empData, "card2");

        employee_details.setBackground(new java.awt.Color(255, 255, 255));
        employee_details.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(102, 0, 102));
        label2.setText("First Name");
        employee_details.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        label3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(102, 0, 102));
        label3.setText("Employee ID");
        employee_details.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        label4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label4.setForeground(new java.awt.Color(102, 0, 102));
        label4.setText("Last Name");
        employee_details.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        label5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(102, 0, 102));
        label5.setText("Phone");
        employee_details.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        label6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label6.setForeground(new java.awt.Color(102, 0, 102));
        label6.setText("Address");
        employee_details.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        label7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label7.setForeground(new java.awt.Color(102, 0, 102));
        label7.setText("Birthday");
        employee_details.add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        label8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label8.setForeground(new java.awt.Color(102, 0, 102));
        label8.setText("Department");
        employee_details.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        label9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label9.setForeground(new java.awt.Color(102, 0, 102));
        label9.setText("Position");
        employee_details.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        label10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label10.setForeground(new java.awt.Color(102, 0, 102));
        label10.setText("Status");
        employee_details.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        label12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label12.setForeground(new java.awt.Color(102, 0, 102));
        label12.setText("Basic Salary");
        employee_details.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        label13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label13.setForeground(new java.awt.Color(102, 0, 102));
        label13.setText("Supervisor");
        employee_details.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txtfirstName.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtfirstName.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtfirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfirstNameActionPerformed(evt);
            }
        });
        employee_details.add(txtfirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 390, -1));

        txtsalary.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtsalary.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtsalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsalaryActionPerformed(evt);
            }
        });
        employee_details.add(txtsalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 390, -1));

        txtlastName.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtlastName.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtlastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlastNameActionPerformed(evt);
            }
        });
        employee_details.add(txtlastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 390, -1));

        txtbirthday.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtbirthday.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtbirthday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbirthdayActionPerformed(evt);
            }
        });
        employee_details.add(txtbirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 390, -1));

        txtaddress.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtaddress.setSelectionColor(new java.awt.Color(255, 0, 255));
        employee_details.add(txtaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 390, -1));

        txtsupervisor.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtsupervisor.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtsupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsupervisorActionPerformed(evt);
            }
        });
        employee_details.add(txtsupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 390, -1));

        txtEmpID.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtEmpID.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtEmpID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpIDActionPerformed(evt);
            }
        });
        employee_details.add(txtEmpID, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 390, -1));

        txtstatus.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtstatus.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstatusActionPerformed(evt);
            }
        });
        employee_details.add(txtstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 390, -1));

        txtphoneNumber.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtphoneNumber.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtphoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtphoneNumberActionPerformed(evt);
            }
        });
        employee_details.add(txtphoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 390, -1));

        txtposition.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtposition.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpositionActionPerformed(evt);
            }
        });
        employee_details.add(txtposition, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 390, -1));

        txtdepartment.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtdepartment.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtdepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdepartmentActionPerformed(evt);
            }
        });
        employee_details.add(txtdepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 390, -1));

        btndelete.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        employee_details.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 140, 40));

        btncreate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btncreate.setText("Create");
        btncreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreateActionPerformed(evt);
            }
        });
        employee_details.add(btncreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 140, 40));

        btnupdate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        employee_details.add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 140, 40));

        label15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label15.setForeground(new java.awt.Color(102, 0, 102));
        label15.setText("Username");
        employee_details.add(label15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 80, -1));

        txtUsername.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtUsername.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        employee_details.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 390, -1));

        label16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label16.setForeground(new java.awt.Color(102, 0, 102));
        label16.setText("Password");
        employee_details.add(label16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 70, -1));

        txtPassword.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtPassword.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        employee_details.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 390, -1));

        label17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label17.setForeground(new java.awt.Color(102, 0, 102));
        label17.setName(""); // NOI18N
        label17.setText("Role");
        employee_details.add(label17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        txtRole.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtRole.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoleActionPerformed(evt);
            }
        });
        employee_details.add(txtRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 390, -1));

        parentPanel.add(employee_details, "card3");

        leavePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableLeave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Date", "Employee ID", "Description", "Leave Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableLeave);

        leavePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 720, 360));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setText("Status");
        leavePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 60, 30));

        comboboxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Rejected", "Approved" }));
        leavePanel.add(comboboxStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 170, 30));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel3.setText("ID");
        leavePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 30));

        txtID.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        leavePanel.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 320, 30));

        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jButton1.setText("Update Leave Request");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        leavePanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 200, 30));

        parentPanel.add(leavePanel, "card4");

        getContentPane().add(parentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 740, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEmpActionPerformed
        // TODO add your handling code here:
        parentPanel.removeAll();
        parentPanel.add(empData);
        parentPanel.repaint();
        parentPanel.revalidate();
        
    }//GEN-LAST:event_viewEmpActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION) {
        dispose(); // Close current dashboard
        new LoginForm().setVisible(true); // Return to login screen
    }
    }//GEN-LAST:event_logoutActionPerformed

    private void empDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empDetailsActionPerformed
        // TODO add your handling code here:
        parentPanel.removeAll();
        parentPanel.add(employee_details);
        parentPanel.repaint();
        parentPanel.revalidate();
    }//GEN-LAST:event_empDetailsActionPerformed

    private void txtfirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfirstNameActionPerformed

    private void txtsalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsalaryActionPerformed

    private void txtlastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlastNameActionPerformed

    private void txtsupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupervisorActionPerformed

    private void txtEmpIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpIDActionPerformed

    private void txtstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstatusActionPerformed

    private void txtphoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtphoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtphoneNumberActionPerformed

    private void txtpositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpositionActionPerformed

    private void txtdepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdepartmentActionPerformed

    private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateActionPerformed
        // TODO add your handling code here:
        String employeeId = txtEmpID.getText().trim();

    if (employeeId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Employee ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        String last_name = txtlastName.getText();
        String first_name  = txtfirstName.getText();
        String birthday  = txtbirthday.getText();
        String address  = txtaddress.getText();
        String phone_number  = txtphoneNumber.getText();
        String status  = txtstatus.getText();
        String position  = txtposition.getText();
        String department  = txtdepartment.getText();
        String supervisor = txtsupervisor.getText();
        double basic_salary  = Double.parseDouble(txtsalary.getText());
        String username  = txtUsername.getText();
        String password  = txtPassword.getText();
        String role  = txtRole.getText();
        double hourly_rate = (double) basic_salary / 160; 
        hourly_rate = Math.round(hourly_rate * 100.0) / 100.0;

        boolean success = Database.createEmployee(employeeId, last_name, first_name, birthday, address, phone_number, status, position, department, supervisor, basic_salary, hourly_rate, username, password, role);

        if (success) {
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            clearTextFields();
            loadEmployeeData(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error adding employee.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_btncreateActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtbirthdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbirthdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbirthdayActionPerformed

    private void txtRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRoleActionPerformed

    private void btnLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeaveActionPerformed
        // TODO add your handling code here:
        parentPanel.removeAll();
        parentPanel.add(leavePanel);
        parentPanel.repaint();
        parentPanel.revalidate();
    }//GEN-LAST:event_btnLeaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String id = txtID.getText();
        String status= comboboxStatus.getSelectedItem().toString();
        
        if (id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }try {
        boolean success = Database.updateLeave(id, status);

        if (success) {
            JOptionPane.showMessageDialog(this, "Leave Request updated successfully!");
            loadLeaveData();
        } else {
            JOptionPane.showMessageDialog(this, "Error Requesting Leave.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
    String employee_id = txtEmpID.getText().trim();
    String last_name = txtlastName.getText().trim();
    String first_name = txtfirstName.getText().trim();
    String username = txtUsername.getText().trim();
    String birthday = txtbirthday.getText().trim();
    String phone_number = txtphoneNumber.getText().trim();
    String address = txtaddress.getText().trim();
    
    String status = txtstatus.getText().trim();
    String position = txtposition.getText().trim();
    String supervisor = txtsupervisor.getText().trim();
    String department = txtdepartment.getText().trim();
    
    double basic_salary = Double.parseDouble(txtsalary.getText().trim());
    double hourly_rate = (double) basic_salary / 160; 
    hourly_rate = Math.round(hourly_rate * 100.0) / 100.0;

    if (employee_id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Employee ID is required for update.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    boolean success = Database.updateEmployee(employee_id, last_name, first_name, username, birthday, phone_number, address, status, position,supervisor, department, basic_salary, hourly_rate);

    if (success) {
        JOptionPane.showMessageDialog(this, "Employee updated successfully!");
        loadEmployeeData();
        clearTextFields();
    } else {
        JOptionPane.showMessageDialog(this, "Error updating employee.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
        
       
        
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
     // Step 1: Prompt for Employee ID
    String empId = JOptionPane.showInputDialog(this, 
        "Enter the Employee ID to delete:", 
        "Delete Employee", 
        JOptionPane.QUESTION_MESSAGE);

    // Step 2: Check if input is empty or canceled
    if (empId == null || empId.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "No Employee ID entered. Deletion canceled.", 
            "Warning", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Step 3: Retrieve Employee Name for Confirmation
    String empName = getEmployeeName(empId);
    if (empName == null) {
        JOptionPane.showMessageDialog(this, 
            "Employee with ID " + empId + " not found.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Step 4: Confirm Deletion
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete '" + empName + "'?", 
        "Confirm Deletion", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.WARNING_MESSAGE);

    // Step 5: Proceed with Deletion if Confirmed
    if (confirm == JOptionPane.YES_OPTION) {
        if (deleteEmployee(empId)) {
            JOptionPane.showMessageDialog(this, 
                "Employee '" + empName + "' has been successfully deleted.", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            loadEmployeeData(); // Refresh employee table
        } else {
            JOptionPane.showMessageDialog(this, 
                "Failed to delete '" + empName + "'. Please try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btndeleteActionPerformed

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
            java.util.logging.Logger.getLogger(HRManagerDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HRManagerDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HRManagerDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HRManagerDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HRManagerDB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLeave;
    private javax.swing.JButton btncreate;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboboxStatus;
    private javax.swing.JScrollPane empData;
    private javax.swing.JButton empDetails;
    private javax.swing.JTable employee_data;
    private javax.swing.JPanel employee_details;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label10;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label15;
    private java.awt.Label label16;
    private java.awt.Label label17;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private javax.swing.JPanel leavePanel;
    private javax.swing.JButton logout;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JTable tableLeave;
    private javax.swing.JTextField txtEmpID;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtbirthday;
    private javax.swing.JTextField txtdepartment;
    private javax.swing.JTextField txtfirstName;
    private javax.swing.JTextField txtlastName;
    private javax.swing.JTextField txtphoneNumber;
    private javax.swing.JTextField txtposition;
    private javax.swing.JTextField txtsalary;
    private javax.swing.JTextField txtstatus;
    private javax.swing.JTextField txtsupervisor;
    private javax.swing.JButton viewEmp;
    // End of variables declaration//GEN-END:variables
}
