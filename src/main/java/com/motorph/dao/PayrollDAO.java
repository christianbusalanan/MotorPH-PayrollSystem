package com.motorph.dao;

import com.motorph.model.Payroll;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {
    
    public List<Payroll> getAllPayrolls() {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM payroll";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Payroll payroll = mapResultSetToPayroll(rs);
                payrolls.add(payroll);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving payrolls: " + e.getMessage());
        }
        
        return payrolls;
    }
    
    public List<Payroll> getPayrollsByEmployeeId(String employeeId) {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM payroll WHERE employee_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Payroll payroll = mapResultSetToPayroll(rs);
                payrolls.add(payroll);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving payrolls for employee: " + e.getMessage());
        }
        
        return payrolls;
    }
    
    public boolean createPayroll(Payroll payroll) {
        String sql = "INSERT INTO payroll (payroll_id, employee_id, period_start, period_end, working_hours, " +
                    "sss_contribution, philhealth_contribution, pagibig_contribution, witholding_tax, " +
                    "rice_subsidy, phone_allowance, clothing_allowance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, payroll.getPayrollId());
            pstmt.setString(2, payroll.getEmployeeId());
            pstmt.setString(3, payroll.getPeriodStart().toString());
            pstmt.setString(4, payroll.getPeriodEnd().toString());
            pstmt.setDouble(5, payroll.getWorkingHours());
            pstmt.setDouble(6, payroll.getSssContribution());
            pstmt.setDouble(7, payroll.getPhilhealthContribution());
            pstmt.setDouble(8, payroll.getPagibigContribution());
            pstmt.setDouble(9, payroll.getWithholdingTax());
            pstmt.setDouble(10, payroll.getRiceSubsidy());
            pstmt.setDouble(11, payroll.getPhoneAllowance());
            pstmt.setDouble(12, payroll.getClothingAllowance());
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error creating payroll: " + e.getMessage());
            return false;
        }
    }
    
    private Payroll mapResultSetToPayroll(ResultSet rs) throws SQLException {
        Payroll payroll = new Payroll();
        payroll.setPayrollId(rs.getString("payroll_id"));
        payroll.setEmployeeId(rs.getString("employee_id"));
        payroll.setPeriodStart(LocalDate.parse(rs.getString("period_start")));
        payroll.setPeriodEnd(LocalDate.parse(rs.getString("period_end")));
        payroll.setWorkingHours(rs.getDouble("working_hours"));
        payroll.setSssContribution(rs.getDouble("sss_contribution"));
        payroll.setPhilhealthContribution(rs.getDouble("philhealth_contribution"));
        payroll.setPagibigContribution(rs.getDouble("pagibig_contribution"));
        payroll.setWithholdingTax(rs.getDouble("witholding_tax"));
        payroll.setRiceSubsidy(rs.getDouble("rice_subsidy"));
        payroll.setPhoneAllowance(rs.getDouble("phone_allowance"));
        payroll.setClothingAllowance(rs.getDouble("clothing_allowance"));
        
        return payroll;
    }
}