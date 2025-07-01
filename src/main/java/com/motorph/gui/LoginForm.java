package com.motorph.gui;

import com.motorph.controller.LoginController;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private LoginController loginController;

    public LoginForm() {
        this.loginController = new LoginController();
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MOTOR PH PAYROLL SYSTEM");
        setResizable(false);
        setSize(960, 502);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanel jPanel1 = new JPanel();
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 331));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabel jLabel1 = new JLabel();
        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 110, 30));

        txtUsername = new JTextField();
        txtUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtUsername.setFont(new java.awt.Font("Verdana", 0, 14));
        txtUsername.setForeground(new java.awt.Color(0, 0, 0));
        txtUsername.setSelectionColor(new java.awt.Color(204, 0, 204));
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 250, 30));

        txtPassword = new JPasswordField();
        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Verdana", 0, 14));
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtPassword.setHorizontalAlignment(JTextField.LEFT);
        txtPassword.setSelectionColor(new java.awt.Color(204, 0, 204));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 250, 30));

        JLabel jLabel3 = new JLabel();
        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 70, -1));

        btnLogin = new JButton();
        btnLogin.setBackground(new java.awt.Color(234, 139, 234));
        btnLogin.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, 40));

        JLabel jLabel5 = new JLabel();
        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 310, 310));

        JLabel jLabel4 = new JLabel();
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/com/background.jpg")));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 500));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/logo.png"));
        setIconImage(icon.getImage());

        pack();
    }

    private void btnLoginActionPerformed(ActionEvent evt) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        
        loginController.handleLogin(username, password, this);
        
        // Clear password field after login attempt
        txtPassword.setText("");
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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
