package com.motorph;

import com.motorph.service.PayrollCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PayrollCalculationServiceTest {
    
    private PayrollCalculationService calculationService;
    
    @BeforeEach
    void setUp() {
        calculationService = new PayrollCalculationService();
    }
    
    @Test
    void testCalculateSSSContribution_LowestBracket() {
        double result = calculationService.calculateSSSContribution(3000);
        assertEquals(135.00, result, 0.01);
    }
    
    @Test
    void testCalculateSSSContribution_MiddleBracket() {
        double result = calculationService.calculateSSSContribution(12000);
        assertEquals(540.00, result, 0.01);
    }
    
    @Test
    void testCalculateSSSContribution_HighestBracket() {
        double result = calculationService.calculateSSSContribution(30000);
        assertEquals(1125.00, result, 0.01);
    }
    
    @Test
    void testCalculatePhilhealthContribution() {
        double result = calculationService.calculatePhilhealthContribution(20000);
        assertEquals(300.00, result, 0.01);
    }
    
    @Test
    void testCalculatePagibigContribution_LowSalary() {
        double result = calculationService.calculatePagibigContribution(1200);
        assertEquals(12.00, result, 0.01);
    }
    
    @Test
    void testCalculatePagibigContribution_HighSalary() {
        double result = calculationService.calculatePagibigContribution(5000);
        assertEquals(100.00, result, 0.01);
    }
    
    @Test
    void testCalculatePagibigContribution_BelowMinimum() {
        double result = calculationService.calculatePagibigContribution(500);
        assertEquals(0.00, result, 0.01);
    }
    
    @Test
    void testCalculateWithholdingTax_NoTax() {
        double result = calculationService.calculateWithholdingTax(20000);
        assertEquals(0.00, result, 0.01);
    }
    
    @Test
    void testCalculateWithholdingTax_FirstBracket() {
        double result = calculationService.calculateWithholdingTax(25000);
        assertEquals(833.40, result, 0.01);
    }
    
    @Test
    void testCalculateGrossIncome() {
        double result = calculationService.calculateGrossIncome(125.0, 160.0);
        assertEquals(20000.00, result, 0.01);
    }
    
    @Test
    void testCalculateNetSalary() {
        double result = calculationService.calculateNetSalary(20000, 2000, 1500);
        assertEquals(19500.00, result, 0.01);
    }
}