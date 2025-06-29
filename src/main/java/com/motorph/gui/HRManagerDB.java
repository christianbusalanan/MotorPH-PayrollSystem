/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.motorph.gui;

import com.motorph.model.Attendance;
import com.motorph.model.Employee;
import com.motorph.model.LeaveRequest;
import com.motorph.service.EmployeeService;
import com.motorph.service.LeaveRequestService;
import com.motorph.dao.AttendanceDAO;
import com.motorph.dao.AttendanceDAO.AttendanceWithEmployee;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author KrisChan
 */
public class HRManagerDB extends javax.swing.JFrame {
    private final EmployeeService employeeService;
    private final LeaveRequestService leaveRequestService;
    private final AttendanceDAO attendanceDAO;
    /**
     * Creates new form HRManager
     */
    public HRManagerDB() {
        this.employeeService = new EmployeeService();
        this.leaveRequestService = new LeaveRequestService();
        this.attendanceDAO = new AttendanceDAO();
        initComponents();
        setLocationRelativeTo(null);
        
        // Load data after UI is initialized
        SwingUtilities.invokeLater(() -> {
            loadEmployeeData();
            loadLeaveData();
            loadAttendanceData();
        });
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
    
    private void loadAttendanceData() {
        System.out.println("HRManagerDashboard: Starting to load attendance data with employee details...");
        
        try {
            List<AttendanceWithEmployee> attendanceRecords = attendanceDAO.getAllAttendanceWithEmployeeDetails();
            System.out.println("HRManagerDashboard: Retrieved " + attendanceRecords.size() + " attendance records with employee details");
            
            DefaultTableModel model = (DefaultTableModel) attendanceTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            for (AttendanceWithEmployee attendance : attendanceRecords) {
                Object[] row = {
                    attendance.getEmployeeId() != null ? attendance.getEmployeeId() : "N/A",
                    attendance.getLastName() != null ? attendance.getLastName() : "N/A",
                    attendance.getFirstName() != null ? attendance.getFirstName() : "N/A",
                    attendance.getDate() != null ? attendance.getDate().toString() : "N/A",
                    attendance.getTimeIn() != null ? attendance.getTimeIn().toString() : "0:00",
                    attendance.getTimeOut() != null ? attendance.getTimeOut().toString() : "0:00"
                };
                model.addRow(row);
                System.out.println("HRManagerDashboard: Added attendance record for " + 
                                 attendance.getEmployeeId() + " - " + attendance.getFullName() + 
                                 " on " + attendance.getDate());
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
        btnAttendance = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        parentPanel = new javax.swing.JPanel();
        empData = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        leavePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        comboLeaveStatus = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtLeaveId = new javax.swing.JTextField();
        updateLeave = new javax.swing.JButton();
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
        txtFirstName = new javax.swing.JTextField();
        txtSalary = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtBirthday = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtSupervisor = new javax.swing.JTextField();
        txtEmployeeId = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        txtDepartment = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();
        btncreate = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        label15 = new java.awt.Label();
        txtUsername = new javax.swing.JTextField();
        label16 = new java.awt.Label();
        txtPassword = new javax.swing.JTextField();
        label17 = new java.awt.Label();
        comboRole = new javax.swing.JComboBox<>();
        comboPosition = new javax.swing.JComboBox<>();
        comboStatus = new javax.swing.JComboBox<>();
        panelAttendance = new javax.swing.JScrollPane();
        attendanceTable = new javax.swing.JTable();

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
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 180, 40));

        viewEmp.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        viewEmp.setText("View Employees");
        viewEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEmpActionPerformed(evt);
            }
        });
        jPanel1.add(viewEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, 40));

        empDetails.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        empDetails.setText("Employee Details");
        empDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empDetailsActionPerformed(evt);
            }
        });
        jPanel1.add(empDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 180, 40));

        btnLeave.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnLeave.setText("Leave Requests");
        btnLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 180, 40));

        btnAttendance.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnAttendance.setText("Attendance");
        btnAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttendanceActionPerformed(evt);
            }
        });
        jPanel1.add(btnAttendance, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 180, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/background.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        parentPanel.setBackground(new java.awt.Color(255, 255, 255));
        parentPanel.setLayout(new java.awt.CardLayout());

        employeeTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        employeeTable.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Employee ID", "Last Name", "First Name", "Birth Date", "Address", "Phone Number", "Status", "Position", "Department", "Supervisor",
                "Basic Salary", "Rate"}
        ));
        employeeTable.getTableHeader().setReorderingAllowed(false);
        empData.setViewportView(employeeTable);

        parentPanel.add(empData, "card2");

        leavePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        leaveTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(leaveTable);

        leavePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 720, 360));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setText("Status");
        leavePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 60, 30));

        comboLeaveStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Rejected", "Approved" }));
        leavePanel.add(comboLeaveStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 170, 30));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel3.setText("ID");
        leavePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 30));

        txtLeaveId.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        leavePanel.add(txtLeaveId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 320, 30));

        updateLeave.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        updateLeave.setText("Update Leave Request");
        updateLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLeaveActionPerformed(evt);
            }
        });
        leavePanel.add(updateLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 200, 30));

        parentPanel.add(leavePanel, "card4");

        employee_details.setBackground(new java.awt.Color(255, 255, 255));
        employee_details.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(102, 0, 102));
        label2.setText("First Name");
        employee_details.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        label3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(102, 0, 102));
        label3.setText("Employee ID");
        employee_details.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        label4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label4.setForeground(new java.awt.Color(102, 0, 102));
        label4.setText("Last Name");
        employee_details.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        label5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(102, 0, 102));
        label5.setText("Phone");
        employee_details.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        label6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label6.setForeground(new java.awt.Color(102, 0, 102));
        label6.setText("Address");
        employee_details.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        label7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label7.setForeground(new java.awt.Color(102, 0, 102));
        label7.setText("Birthday");
        employee_details.add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        label8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label8.setForeground(new java.awt.Color(102, 0, 102));
        label8.setText("Department");
        employee_details.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        label9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label9.setForeground(new java.awt.Color(102, 0, 102));
        label9.setText("Position");
        employee_details.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 20));

        label10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label10.setForeground(new java.awt.Color(102, 0, 102));
        label10.setText("Status");
        employee_details.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, -1, 20));

        label12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label12.setForeground(new java.awt.Color(102, 0, 102));
        label12.setText("Basic Salary");
        employee_details.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        label13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label13.setForeground(new java.awt.Color(102, 0, 102));
        label13.setText("Supervisor");
        employee_details.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        txtFirstName.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtFirstName.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });
        employee_details.add(txtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 260, -1));

        txtSalary.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtSalary.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalaryActionPerformed(evt);
            }
        });
        employee_details.add(txtSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 140, -1));

        txtLastName.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtLastName.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });
        employee_details.add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 260, -1));

        txtBirthday.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtBirthday.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtBirthday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBirthdayActionPerformed(evt);
            }
        });
        employee_details.add(txtBirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 280, -1));

        txtAddress.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtAddress.setSelectionColor(new java.awt.Color(255, 0, 255));
        employee_details.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 620, -1));

        txtSupervisor.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtSupervisor.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupervisorActionPerformed(evt);
            }
        });
        employee_details.add(txtSupervisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 620, -1));

        txtEmployeeId.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtEmployeeId.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtEmployeeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployeeIdActionPerformed(evt);
            }
        });
        employee_details.add(txtEmployeeId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 160, -1));

        txtPhoneNumber.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtPhoneNumber.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });
        employee_details.add(txtPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 260, -1));

        txtDepartment.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtDepartment.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });
        employee_details.add(txtDepartment, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 170, -1));

        btndelete.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        employee_details.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 120, 30));

        btncreate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btncreate.setText("Create");
        btncreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreateActionPerformed(evt);
            }
        });
        employee_details.add(btncreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 120, 30));

        btnupdate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        employee_details.add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 120, 30));

        label15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label15.setForeground(new java.awt.Color(102, 0, 102));
        label15.setText("Username");
        employee_details.add(label15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 80, -1));

        txtUsername.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtUsername.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        employee_details.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 390, -1));

        label16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label16.setForeground(new java.awt.Color(102, 0, 102));
        label16.setText("Password");
        employee_details.add(label16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 70, -1));

        txtPassword.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtPassword.setSelectionColor(new java.awt.Color(255, 0, 255));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        employee_details.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 390, -1));

        label17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label17.setForeground(new java.awt.Color(102, 0, 102));
        label17.setName(""); // NOI18N
        label17.setText("Role");
        employee_details.add(label17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        comboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employee", "HR Manager", "Payroll Staff" }));
        comboRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoleActionPerformed(evt);
            }
        });
        employee_details.add(comboRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 130, -1));

        comboPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chief Executive Officer", "Chief Operating Officer", "Chief Finance Officer", "Chief Marketing Officer", "IT Operations and Systems", "HR Manager", "HR Team Leader", "HR Rank and File", "Accounting Head", "Payroll Manager", "Payroll Team Leader", "Payroll Rank and File", "Account Manager", "Account Team Leader", "Account Rank and File", "Sales & Marketing", "Supply Chain and Logistics", "Customer Service and Relations" }));
        comboPosition.setSelectedIndex(11);
        comboPosition.setToolTipText("");
        employee_details.add(comboPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 260, -1));

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Probationary" }));
        employee_details.add(comboStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 280, -1));

        parentPanel.add(employee_details, "card3");

        attendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void txtSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaryActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void txtSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupervisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupervisorActionPerformed

    private void txtEmployeeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployeeIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmployeeIdActionPerformed

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNumberActionPerformed

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentActionPerformed

    private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateActionPerformed
        // TODO add your handling code here:
       System.out.println("HRManagerDashboard: Create employee button clicked");
        
        try {
            // Validate required fields
            if (txtEmployeeId.getText().trim().isEmpty() || 
                txtFirstName.getText().trim().isEmpty() || 
                txtLastName.getText().trim().isEmpty() ||
                txtUsername.getText().trim().isEmpty() ||
                txtPassword.getText().trim().isEmpty() ||
                txtSalary.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all required fields:\n" +
                    "- Employee ID\n" +
                    "- First Name\n" +
                    "- Last Name\n" +
                    "- Username\n" +
                    "- Password\n" +
                    "- Basic Salary", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create employee object
            Employee employee = new Employee();
            employee.setEmployeeId(txtEmployeeId.getText().trim());
            employee.setFirstName(txtFirstName.getText().trim());
            employee.setLastName(txtLastName.getText().trim());
            employee.setUsername(txtUsername.getText().trim()); // This will be stored in user table
            
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
            
            // Parse salary
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

            System.out.println("HRManagerDashboard: Creating employee with normalized database structure");
            System.out.println("HRManagerDashboard: Employee ID: " + employee.getEmployeeId());
            System.out.println("HRManagerDashboard: Username: " + employee.getUsername());
            System.out.println("HRManagerDashboard: Role: " + role);

            // Create employee using service (handles both user and employee tables)
            boolean success = employeeService.createEmployee(employee, password, role);

            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Employee created successfully!\n\n" +
                    "Employee ID: " + employee.getEmployeeId() + "\n" +
                    "Username: " + employee.getUsername() + "\n" +
                    "Role: " + role + "\n\n" +
                    "Both User and Employee records have been created.", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearEmployeeFields();
                loadEmployeeData(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error creating employee.\n\n" +
                    "Possible causes:\n" +
                    "- Employee ID already exists\n" +
                    "- Username already exists\n" +
                    "- Database connection error\n\n" +
                    "Please check the console for detailed error messages.", 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("HRManagerDashboard: Error creating employee: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Unexpected error creating employee:\n" + e.getMessage() + 
                "\n\nPlease check the console for detailed error information.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btncreateActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtBirthdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBirthdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBirthdayActionPerformed

    private void btnLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeaveActionPerformed
        // TODO add your handling code here:
        parentPanel.removeAll();
        parentPanel.add(leavePanel);
        parentPanel.repaint();
        parentPanel.revalidate();
    }//GEN-LAST:event_btnLeaveActionPerformed

    private void updateLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLeaveActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_updateLeaveActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
    JOptionPane.showMessageDialog(this, "Update functionality not yet implemented.");
           
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_btndeleteActionPerformed

    private void comboRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboRoleActionPerformed

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
            java.util.logging.Logger.getLogger(HRManagerDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HRManagerDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HRManagerDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HRManagerDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(HRManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new HRManagerDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendanceTable;
    private javax.swing.JButton btnAttendance;
    private javax.swing.JButton btnLeave;
    private javax.swing.JButton btncreate;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboLeaveStatus;
    private javax.swing.JComboBox<String> comboPosition;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JScrollPane empData;
    private javax.swing.JButton empDetails;
    private javax.swing.JTable employeeTable;
    private javax.swing.JPanel employee_details;
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
    private javax.swing.JTable leaveTable;
    private javax.swing.JButton logout;
    private javax.swing.JScrollPane panelAttendance;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBirthday;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtEmployeeId;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLeaveId;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtSupervisor;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JButton updateLeave;
    private javax.swing.JButton viewEmp;
    // End of variables declaration//GEN-END:variables
}
