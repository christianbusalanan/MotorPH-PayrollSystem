package com.motorph.service;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReportService {
    
    public void generatePayslip(String payrollId) {
        try {
            JasperReport report = JasperCompileManager.compileReport("payslip.jrxml");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("payroll_id", payrollId);
            
            InputStream logoStream = getClass().getResourceAsStream("/images/logo.png");
            parameters.put("logo", logoStream);
            
            Connection conn = DriverManager.getConnection("jdbc:sqlite:MotorPH Payroll System.db");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            JasperViewer.viewReport(print, false);
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate payslip: " + e.getMessage());
        }
    }
}
