package Test;

import com.motorph.model.Payroll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class PayrollTest {
    
    private Payroll payroll;
    
    @BeforeEach
    void setUp() {
        payroll = new Payroll();
    }
    
    @Test
    void testDefaultConstructor() {
        assertNotNull(payroll);
        assertNull(payroll.getPayrollId());
        assertEquals(0.0, payroll.getWorkingHours());
    }
    
    @Test
    void testParameterizedConstructor() {
        LocalDate start = LocalDate.of(2023, 1, 1);
        LocalDate end = LocalDate.of(2023, 1, 15);
        
        Payroll p = new Payroll(
            "PAY001", "EMP001", start, end, 80.0,
            500.0, 300.0, 100.0, 1000.0,
            1500.0, 800.0, 1000.0
        );
        
        assertEquals("PAY001", p.getPayrollId());
        assertEquals("EMP001", p.getEmployeeId());
        assertEquals(start, p.getPeriodStart());
        assertEquals(end, p.getPeriodEnd());
        assertEquals(80.0, p.getWorkingHours());
        assertEquals(500.0, p.getSssContribution());
        assertEquals(300.0, p.getPhilhealthContribution());
        assertEquals(100.0, p.getPagibigContribution());
        assertEquals(1000.0, p.getWithholdingTax());
        assertEquals(1500.0, p.getRiceSubsidy());
        assertEquals(800.0, p.getPhoneAllowance());
        assertEquals(1000.0, p.getClothingAllowance());
    }
    
    @Test
    void testGetGrossIncome() {
        payroll.setWorkingHours(160.0);
        double grossIncome = payroll.getGrossIncome(125.0);
        assertEquals(20000.0, grossIncome, 0.01);
    }
    
    @Test
    void testGetTotalDeductions() {
        payroll.setSssContribution(500.0);
        payroll.setPhilhealthContribution(300.0);
        payroll.setPagibigContribution(100.0);
        payroll.setWithholdingTax(1000.0);
        
        assertEquals(1900.0, payroll.getTotalDeductions(), 0.01);
    }
    
    @Test
    void testGetTotalBenefits() {
        payroll.setRiceSubsidy(1500.0);
        payroll.setPhoneAllowance(800.0);
        payroll.setClothingAllowance(1000.0);
        
        assertEquals(3300.0, payroll.getTotalBenefits(), 0.01);
    }
    
    @Test
    void testGetNetSalary() {
        payroll.setWorkingHours(160.0);
        payroll.setSssContribution(500.0);
        payroll.setPhilhealthContribution(300.0);
        payroll.setPagibigContribution(100.0);
        payroll.setWithholdingTax(1000.0);
        payroll.setRiceSubsidy(1500.0);
        payroll.setPhoneAllowance(800.0);
        payroll.setClothingAllowance(1000.0);
        
        // Gross: 160 * 125 = 20000
        // Total Deductions: 500 + 300 + 100 + 1000 = 1900
        // Total Benefits: 1500 + 800 + 1000 = 3300
        // Net: 20000 + 3300 - 1900 = 21400
        assertEquals(21400.0, payroll.getNetSalary(125.0), 0.01);
    }
}