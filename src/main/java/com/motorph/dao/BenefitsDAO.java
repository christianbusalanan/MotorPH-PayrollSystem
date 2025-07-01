package com.motorph.dao;

import com.motorph.model.Benefits;
import java.sql.*;

public class BenefitsDAO {
    
    public Benefits getBenefitsByPosition(String position) {
        String sql = "SELECT * FROM benefits WHERE position = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, position);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Benefits(
                    rs.getString("position"),
                    rs.getDouble("rice_subsidy"),
                    rs.getDouble("phone_allowance"),
                    rs.getDouble("clothing_allowance")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving benefits: " + e.getMessage());
        }
        
        return null;
    }
}